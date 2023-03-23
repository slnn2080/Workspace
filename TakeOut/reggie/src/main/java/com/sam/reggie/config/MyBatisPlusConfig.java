package com.sam.reggie.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置mp的分页插件
@Configuration
public class MyBatisPlusConfig {

  // 将 MybatisPlusInterceptor 对象交给IOC容器管理, 作用: 用于配置MyBatis的插件
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    // 1. 创建 MyBatis-Plus 的插件容器
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

    // 2. 创建分页插件对象 传入MysqlDB
    PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);

    // 3. 将插件添加到插件容器中
    mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

    return mybatisPlusInterceptor;
  }
}
