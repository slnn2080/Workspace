package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sam.reggie.common.Result;
import com.sam.reggie.entity.User;
import com.sam.reggie.service.UserService;
import com.sam.reggie.utils.SMSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private UserService userService;

  // 发送验证码:
  @GetMapping("/sendMsg/{phone}")
  public Result<String> code(@PathVariable String phone, HttpServletRequest req) {

    // 获取手机号
    System.out.println("phone = " + phone);

    // 判断手机号是否为空 如果为空则不发送短信
    if(StringUtils.isNotBlank(phone)) {
      // 生成随机的验证码
      String code = SMSUtils.generateCode();

      // 调用阿里云提供的短信服务 发送短信
      // SMSUtils.sendMessage("瑞吉外卖", "SMS-101012", phone, code)

      // 将验证码保存到session, 用于一会校验前端输入的验证码是否正确, 手机号作为key
      // req.getSession().setAttribute(phone, code);

      // 将手机验证码保存到Redis中, 并且设置有效期为5分钟
      redisTemplate.opsForValue().set(phone, code, 5L, TimeUnit.MINUTES);
      return Result.success(code);
    }

    return Result.error("短信发送失败");
  }

  @PostMapping("login")
  /*
    接收前端参数方案:
      前端参数是 phone 和 code, 没有可以同时承装它们两个的实体类 有如下的两种方式对应
      1. UserDto, 我们额外的扩展code属性
      2. 使用 Map 来接收, Map中的key就是 phone 和 code 和前端的json是一一对应的
  */
  public Result<User> login(@RequestBody Map<String, String> map, HttpSession session) {

    // 获取手机号 和 验证码
    String phone = map.get("phone").toString();
    String code = map.get("code").toString();

    // 根据手机号获取session中的验证码
    // String codeInSession = (String) session.getAttribute(phone);

    // 从Redis中获取我们缓存的验证码
    String codeInRedis = (String) redisTemplate.opsForValue().get(phone);


    // 进行验证码的比对
    if(codeInRedis != null && codeInRedis.equals(code)) {
      // 如果验证码比对成功 则说明登录成功, 判断当前手机号是否是新用户
      LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
      userLambdaQueryWrapper.eq(User::getPhone, phone);
      User user = userService.getOne(userLambdaQueryWrapper);

      // 说明他是一个新用户 我们偷偷的进行无感注册
      if(user == null) {
        user = new User();
        user.setPhone(phone);
        userService.save(user);
      }

      // 登录成功后将用户的id放到session中 过滤器中会校验
      session.setAttribute("user", user.getId());

      // 如果用户登录成功则删除redis中缓存的验证码
      redisTemplate.delete(phone);

      return Result.success(user);
    }

    return Result.error("登录失败");

  }

}
