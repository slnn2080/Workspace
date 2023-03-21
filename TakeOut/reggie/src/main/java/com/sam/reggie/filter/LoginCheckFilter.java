package com.sam.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.sam.reggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

  // Spring-Core提供的一个类 路径匹配器: 专门用来比对路径的工具类, 支持通配符的写法
  public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;

    log.info("拦截到请求: {}", req.getRequestURI());


    // 1. 获取本次请求的uri
    String uri = req.getRequestURI();


    // 2. 判断本次请求是否需要处理(是否需要检查用户的登录状态), 因为并不是访问所有的请求都需要用户是登录状态
    // 定义白名单(不需要处理的请求)
    String[] urls = new String[] {
        "/employee/login",
        "/employee/logout",
        "/backend/**",
        "/front/**",
    };
    /*
      如: 我们的请求路径为 /backend/index.html 时, 我们的 "/backend/** 是不一致的, 我们希望 /backend/** 能够匹配上 /backend/index.html 这里就需要使用 AntPathMatcher 路径匹配器
    */
    boolean check = check(uri, urls);


    // 3. 如果不需要处理 则直接放行
    // 档次请求在白名单中的话, 我们直接放行
    if(check) {
      filterChain.doFilter(req, res);
      return;
    }


    // 4. 判断登录状态, 如果已登录 则直接放行
    // 需要处理, 判断用户是否登录
    Object empId = req.getSession().getAttribute("employee");
    if(empId != null) {
      // 已经挡路
      filterChain.doFilter(req, res);
      return;
    }


    // 5. 如果未登录则返回未登录结果
    /*
      前端的响应拦截器中 会根据code是否为0 和 msg是否为 NOTLOGIN 来判断用户在未登录的情况下需要跳转到登录页(同时会删除本地存储中的userInfo

      后台只需要返回数据, 前端来控制页面跳转
      因为这里是Filter不是Controller 我们响应数据的时候需要使用输出流
    */
    // alibaba-fastJson
    String json = JSON.toJSONString(Result.error("NOTLOGIN"));
    res.getWriter().write(json);
    return;
  }

  // 封装一个方法: 检查当前请求是否在白名单中(放行)
  // urls: 白名单
  public boolean check(String requestURI, String[] urls) {
    for (String url : urls) {
      boolean match = PATH_MATCHER.match(url, requestURI);
      // 如果返回true 说明匹配上了
      if(match) return true;
    }

    return false;
  }
}
