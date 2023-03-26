package com.sam.reggie.common;

// 自定义业务异常类
public class CustomException extends RuntimeException {

  // 定义构造器: 传入异常信息
  public CustomException(String message) {
    super(message);
  }
}
