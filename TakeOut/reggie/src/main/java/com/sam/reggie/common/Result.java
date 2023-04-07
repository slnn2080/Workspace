package com.sam.reggie.common;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
  private Integer code;
  private String msg;
  private T data;

  // 动态数据
  private Map map = new HashMap();

  // 成功的方法
  public static <T> Result<T> success(T object) {
    Result<T> result = new Result<>();
    result.data = object;
    result.code = 1;

    return result;
  }

  // 失败的方法
  public static <T> Result<T> error(String msg) {
    Result<T> result = new Result<>();
    result.msg = msg;
    result.code = 0;

    return result;
  }

  // 动态数据
  public Result<T> add(String key, Object value) {
    this.map.put(key, value);
    return this;
  }
}
