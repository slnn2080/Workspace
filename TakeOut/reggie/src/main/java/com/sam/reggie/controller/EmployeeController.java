package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sam.reggie.common.Result;
import com.sam.reggie.entity.Employee;
import com.sam.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

  // 注入service
  @Autowired
  private EmployeeService employeeService;


  // 处理登录请求
  @PostMapping("/login")
  public Result<Employee> login(@RequestBody Employee employee, HttpServletRequest req) {
    // 1. 将页面提交的密码 password 进行 md加密 处理
    String password = employee.getPassword();
    // 参数: byte[] bytes
    password = DigestUtils.md5DigestAsHex(password.getBytes());


    // 2. 根据页面提交的用户名 username 查询数据库
    // 参数: queryWrapper
    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Employee::getUsername, employee.getUsername());
    // 用户表中用户名字段唯一 所以可以通过用户名来获取用户
    Employee emp = employeeService.getOne(queryWrapper);


    // 3. 如果没有查询到则返回 登录失败 (因为用户名不存在)
    if(emp == null) {
      // 如果没有查询到结果 直接返回登录失败
      return Result.error("登录失败");
    }


    // 4. 密码比对, 如果不一致则返回 登录失败
    // emp是从数据库查询出来的结果, 里面的密码是加密后的
    if(!emp.getPassword().equals(password)) {
      return Result.error("登录失败");
    }

    // 5. 查看员工状态, 如果为已禁用状态 则返回员工已禁用结果
    // 到这里就是密码比对成功, 0为禁用状态
    if(emp.getStatus() == 0) {
      return Result.error("账号已经禁用");
    }

    // 6. 登录成功, 将员工id存入session并返回成功结果
    req.getSession().setAttribute("employee", emp.getId());
    return Result.success(emp);
  }


  // 处理退出登录请求
  @PostMapping("/logout")
  public Result<String> logout(HttpServletRequest req) {
    // 1. 清理session中保存的当前登录员工的id
    req.getSession().removeAttribute("employee");

    // 2. 返回 退出成功 的msg
    return Result.success("退出成功");
  }
}
