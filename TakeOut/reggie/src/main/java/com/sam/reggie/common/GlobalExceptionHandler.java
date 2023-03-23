package com.sam.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

// 全局异常处理器, 使用annotations属性 拦截Controller层中使用了@RestController注解的类
@ControllerAdvice(annotations = {RestController.class, Controller.class})
// 使用该注解, 最终将JSON数据进行返回
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

  // 异常处理方法: 使用该注解 指明要对哪种异常进行处理
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  // 处理方法中要声明该异常类型的形参
  public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
    log.error("--- > ", ex.getMessage());

    // SQLIntegrityConstraintViolationException 里面包含了很多sql的异常异常 不一定是我们想要捕捉的 添加了重复的username "Duplicate entry" 异常 所以我们要判断下 异常信息中是否有 Duplicate entry 关键子 如果有我们就能确定是我们想要的异常

    if(ex.getMessage().contains("Duplicate entry")) {
      // 违反了唯一约束的异常
      String[] strings = ex.getMessage().split(" ");
      // 获取 "zhangsan" 部分的字符串 也就是哪个字段重复了
      String msg = strings[2] + "已存在";
      return Result.error(msg);
    }

    return Result.error("未知错误");
  }
}
