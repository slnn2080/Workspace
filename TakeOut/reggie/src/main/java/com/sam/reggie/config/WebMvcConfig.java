package com.sam.reggie.config;

import com.sam.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 设置 请求资源 映射到 哪个目录下 addResourceHandler资源处理器, 主要路径中 backend 就会映射到
    registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
  }

  // 重写extendMessageConverters方法: 扩展mvc框架的消息转换器
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    log.info("消息转换器");

    // 创建消息转换器对象: 将controller中返回的结果 转成 相对应的json, 再通过输出流响应给页面, 前端收到的json数据就是通过消息转换器传输的
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

    // 设置自己配置的对象转换器
    messageConverter.setObjectMapper(new JacksonObjectMapper());

    // 将消息转换器对象追加到mvc框架中的转换器集合中, 参数1是索引, 表示转换器的顺序, 将我们自己设置的消息转换器设置为首位
    converters.add(0, messageConverter);
  }
}
