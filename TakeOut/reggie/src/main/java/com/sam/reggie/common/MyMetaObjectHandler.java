package com.sam.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

// 自定义元数据对象处理器
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

  /*
    metaObject参数: 元数据对象
    {
      originalObject: 它里面是 employee对象
      objectWrapper:
      objectFactory:
      objectWrapperFactory
      reflectorFactory:
    }
  */
  // 当执行 insert语句的时候 会调用该方法
  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("公共字段自动填充 [insert]");

    Long currentId = BaseContext.getCurrentId();

    metaObject.setValue("createTime", LocalDateTime.now());
    metaObject.setValue("updateTime", LocalDateTime.now());
    // createUser需要添写 当前用户的Long id, 我们在控制层的时候当前用户的id是从session中获取的 但是我们在当前类中获取不到session对象 暂时我们先写死
    metaObject.setValue("createUser", currentId);
    metaObject.setValue("updateUser", currentId);
  }

  // 当执行 update语句的时候 会调用该方法
  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("公共字段自动填充 [update]");

    Long currentId = BaseContext.getCurrentId();

    metaObject.setValue("updateTime", LocalDateTime.now());
    metaObject.setValue("updateUser", currentId);
  }
}
