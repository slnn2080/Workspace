package com.sam.reggie.common;

import com.sam.reggie.utils.SMSUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.AntPathMatcher;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class FeatureTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  public void code() {
    String code = SMSUtils.generateCode();
    System.out.println("code = " + code);
  }

  @Test
  public void matcherTest() {
    AntPathMatcher matcher = new AntPathMatcher();
    String url = "/backend/**";
    boolean match = matcher.match(url, "/backend/index.html");
    System.out.println("match = " + match);
  }

  @Test
  public void localDateTimeTest() {
    LocalDateTime now = LocalDateTime.now();
    System.out.println("now = " + now);
    // 2023-03-22T21:43:56.549
  }

  @Test
  public void testAns() {
    int num = 3;
    switch (num) {
      case 1:
        System.out.println("巨蟹");
        break;
      case 2:
        System.out.println("射手");
        break;
      case 3:
        System.out.println("双子");
      case 4:
        System.out.println("白羊");
        break;
      default:
        System.out.println("天蝎");
    }
  }

  @Test
  public void testRedis() {

    ValueOperations valueOperations = redisTemplate.opsForValue();
    valueOperations.set("java-redis", "java");
    String name = (String) valueOperations.get("java-redis");
    System.out.println("name = " + name);
  }

}

