# 软件开发流程
一般我们开发软件都会遵循这个流程

```s
| - 需求分析
| - 设计
| - 编码
| - 测试
| - 上线运维
```

<br>

### 需求分析:
这个部分主要完成产品原型 和 需求规格说明书的编写

<br>

**产品原型:**  
通过网页的形式展现下当前我们要开发的项目大体的结构 比如
- 有哪些页面
- 每个页面的结构是什么样子的 列表页面? 表单页面?
- 点击某个按钮之后需要查询什么数据 有什么样的效果

<br>

它就是一款产品在成型之前的一个简单的框架, 就是将页面的排版布局展现出来 使产品的初步构思有一个可视化的展示

通过原型展示 可以更加直观的了解项目的需求和提供的功能

<br>

**需求规格说明书:**  
一般是一个word文档 通过它展现当前的项目有哪些功能 会有文字的说明

<br>

### 设计:
- 产品文档设计
- 概要设计
- 详细设计

<br>

**UI界面设计:**  
它需要将当前项目页面效果展现出来 最终的开发效果基本上和UI设计一直

<br>

**数据库设计:**   
包含当前的项目有几个数据库 每个数据库中的表, 具体表的字段是什么样的

<br>

### 编码:
该阶段只要有开发来完成 编写项目代码 做单元测试

<br>

### 测试:
由测试人员编写测试用例, 最终给出测试报告, 测试报告没有问题后 就可以上线了

<br>

### 上线运维:
一般会有专门的运维人员来负责 软件环境安装 和 配置

<br><br>

# 角色分工

### 项目经理:
负责对整个项目的把控, 任务分配 把控进度

<br>

### 产品经理:
进行需求调研, 输出需求调研文档 设计产品原型等

<br>

### UI设计师:
根据产品原型输出界面效果图

<br>

### 架构师:
从技术层面整体把控项目 技术架构的设计, 技术选型等

<br>

### 开发工程师:
代码实现

<br>

### 测试工程师:
编写测试用例 输出测试报告

<br>

### 运维工程师:
软件环境搭建 项目上线

<br><br>

# 软件环境

### 开发环境(development)
开发人员在开发阶段使用的环境 一般外部用户无法访问

<br>

### 测试环境(testing):
专门给测试人员使用的环境 用于测试项目 一般外部用户无法访问

<br>

### 生产环境(production):
即上线环境, 正式提供对外服务的环境

<br><br>

# 项目介绍
本项目(瑞吉外卖)是专门为餐饮企业定制的一款软件产品, 包括系统管理后台和移动端应用两部分

其中系统管理后台提供给餐饮企业内部员工使用, 可以对餐厅的菜品 套餐 订单等进行管理维护

移动端应用主要提供给消费者使用 可以在线浏览菜品 添加购物车 下单等

<br>

### 本项目共分为3期开发
1. 主要实现基本需求 其中移动端应用通过h5实现 用户可以通过手机浏览器访问

2. 主要针对移动端应用进行改进 使用微信小程序实现, 用户使用起来更加的方便

3. 主要针对系统进行优化升级 提供系统的访问性能

<br><br>

# 前端相关

<br>

## 功能架构:
- 手机号登录
- 微信登录
- 地址管理
- 历史订单
- 菜品规格
- 购物车
- 下单
- 菜品浏览

<br><br>

# 后台相关

<br>

## 功能架构:
- 分类管理
- 菜品管理
- 套餐管理
- 菜品口味管理
- 员工登录
- 员工退出
- 员工管理
- 订单管理

<br><br>

# 数据库相关
我们整个项目一共涉及到了11张表

|序号|表名|说明|
|:--:|:--:|:--|
|1|employee|员工表|
|2|category|菜品和套餐分类表|
|3|dish|菜品表|
|4|setmeal|套餐表|
|5|setmeal_dish|套餐菜品关系表|
|6|dish_flavor|菜品口味关系表|
|7|user|用户表(c端)|
|8|address_book|地址簿表|
|9|shopping_cart|购物车表|
|10|orders|订单表|
|11|order_detail|订单明细表|


<br>

**员工表:**  
存储员工的登录账号信息 

<br>

**菜品和套餐分类表:**  
前端展示页面的时候都会按照菜品的分类来展示

<br><br>

# 开发环境的搭建

## 创建Maven项目

**要点:**  
创建完项目后, 注意检查项目的编码, maven仓库配置 jdk配置等

<br>  

### 检查Maven
ctrl + , -> 搜索 maven
1. 查看是否使用的本地仓库
2. 点击runner选项卡, 查看jre版本是否为jdk1.8

<br>

### 检查JDK
ctrl + ; -> Project选项卡
1. 查看Project SDK部分是否是1.8

<br><br>

## 配置pom.xml

### 导入依赖
这里使用的依赖都是老师提供的在 资料/项目配置文件 下
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.4.5</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <groupId>com.sam</groupId>
  <artifactId>reggie</artifactId>
  <version>1.0</version>

  <properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <scope>compile</scope>
    </dependency>

    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.4.2</version>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.20</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.76</version>
    </dependency>

    <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <scope>runtime</scope>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid-spring-boot-starter</artifactId>
      <version>1.1.23</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.4.5</version>
      </plugin>
    </plugins>
  </build>

</project>
```

<br><br>

## 配置 applicatiom.yml
```yml
server:
  port: 8080


spring:
  application:
    # 可选: 我们可以配置项目名称 如果不配置则默认使用工程名
    name: reggie_take_out
  datasource:

    # 这里使用 druid 报错 我们把它删掉就可以了
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/reggie?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: root


mybatis-plus:
  configuration:
    #在映射实体或者属性时，将数据库中表名和字段名中的下划线去掉，按照驼峰命名法映射
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      id-type: ASSIGN_ID
```

<br><br>

## 上述问题:
SpringBoot+Druid启动报错Failed to configure a DataSource: 'url' attribute is not specified

<br>

### 错误报告:
```
Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).
```

<br>

查遍了所有可能的原因，最后是因为springboot数据源用了Druid，SpringBoot默认是不支持Druid数据源的，需要手动初始化DruidDataSource对象
```java
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.main.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.main.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.main.password"));
        return dataSource;
    }
}
```

<br><br>

## 编写启动类
因为我们创建的是Maven工程 所以目录结构 需要我们自己组织 同时我们需要自己编写启动类

<br>

### 要点:
**@Slf4j注解:**  
输出日志 方便我们调试, 它是lombok提供的注解  
使用该注解后 我们可以使用 ``log.info("输出日志")``  相当于在项目中 console.log

```java
package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功...");
    }
}

```

<br><br>

## 静态资源问题: 前面页面的存放
这个项目中的页面都是写好的 我们直接导入使用就可以
- front: 前台页面
- backend: 后台管理页面

<br>

我们将它们两个放入到如下的目录下
```
| - resources
  | - backend
  | - front
```

<br>

### 要点:
我们将SpringBoot的时候说 对于我们引入的静态资源 我们会要求放在如下的两个目录下
- static
- templates

而现在我们的静态资源分别在 backend 和 front 下, 这样就存在一个问题

**默认情况下我们直接访问这些页面是访问不到的, 默认只能访问到 static or templates 下的资源**

<br>

比如我们访问如下的资源时, 会报404错误, 也就是说我们访问tomcat服务器的时候没有找到对应的页面
```s
localhost:8080/backend/index.html
```

<br>

### 解决方式:
我们要**通过配置类的方式 解决静态资源的映射问题**

告诉我们的mvc框架, backend 和 front 目录下存放的是静态资源

设置静态资源映射 我们通过浏览器发送的请求 比如我们请求的是 /backend/index.html 它就会映射到 /backend目录下的index.html
```java
package com.sam.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    log.info("开始进行静态资源映射");
    // 设置 请求资源 映射到 哪个目录下 addResourceHandler资源处理器, 主要路径中 backend 就会映射到
    registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
  }
}
```

<br>

1. 我们实现addResourceHandlers()方法 通过该方法来设置 请求资源 映射到 哪个目录下
2. registry.addResourceHandler("/backend/**"): 如果请求路径中含有 backend 则映射到 指定的目录
3. addResourceLocations("classpath:/backend/"): 映射到classpath下的某个目录

<br>

# 登录功能

## 需求分析
需求分析一般都是从页面原型说起, 我们可以先找到 登录页面 的原型 **登录.html**

我们在页面上输入 用户名 和 密码 点击登录 就会发送请求 请求就会到服务端的组件

```
请求 -> Controller -> Service -> Mapper -> DB
```

<br>

### 服务器处理前台请求后 响应的数据格式：
我们观察前端代码, 可以观察到 后台返回的数据里面应该有 这样前端才能取出使用
```js
{
  code:
  data: 
  msg:
}
```

<br><br>

## 登录: 准备工作

### 创建实体类 Employee 和 employee表进行映射
数据库中的一张表 映射为 一个实体类

```
com.sam.reggie.entity
```

<br>

**要点:**  
1. 实体类实现Serializable
2. @TableField注解

```java
/**
 * 员工实体
 */
@Data
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String name;

  private String password;

  private String phone;

  private String sex;

  //身份证号码
  private String idNumber;

  private Integer status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  @TableField(fill = FieldFill.INSERT)
  private Long createUser;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;

}

```

<br>

### 创建 Controller
1. 使用 @RestController 注解进行标识
2. 使用 @RequestMapping("/employee") 注解 设置匹配请求uri的前缀
3. 注入service

```java
package com.sam.reggie.controller;

import com.sam.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

  // 注入service
  @Autowired
  private EmployeeService employeeService;
}

```


<br>

### 创建 EmployeeService
1. 自定义service接口 继承 父接口IService并传入泛型
2. 自定义service接口 继承 ServiceImpl并传入mapper和对应实体类 并 实现我们自己定义的service接口
3. service实现类要使用 @Service 注解

<br>

**EmployeeService接口:**
```java
package com.sam.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sam.reggie.entity.Employee;

public interface EmployeeService extends IService<Employee> {
}

```

<br>

**EmployeeServiceImpl:**  
```java
package com.sam.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sam.reggie.entity.Employee;
import com.sam.reggie.mapper.EmployeeMapper;
import com.sam.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

```

<br>

### 创建 EmployeeMapper接口
1. 让它继承BaseMapper并传入泛型 也就是我们要操作的表
2. 使用 @Mapper 注解 
```java
package com.sam.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sam.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}

```

<br><br>

## 设置: 通用的结果类
此类事一个通用的结果类, 服务端响应的所有结果最终都会包装成此种类型返回给前端页面

一个项目中我们会编写很多的Controller 每个控制器方式又都是响应数据, 而一般情况下 我们给前端返回的数据都是一样的格式

我们就将数据封装到这个结果类中

<br>

### 封装方式1:
1. 定义了属性
2. 创建了
  - 成功 对应的 结果对象
  - 失败 对应的 结果对象
  - 往结果对象中追加 kv 的方法
  
```java
package com.sam.reggie.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {

  //编码：1成功，0和其它数字为失败
  private Integer code; 

  //错误信息
  private String msg; 

  //数据
  private T data; 

  //动态数据
  private Map map = new HashMap(); 

  // 响应成功时返回的数据
  public static <T> R<T> success(T object) {
      R<T> r = new R<T>();
      r.data = object;
      r.code = 1;
      return r;
  }

  // 响应失败时返回的数据
  public static <T> R<T> error(String msg) {
      R r = new R();
      // 失败的时候 的 错误信息
      r.msg = msg;
      r.code = 0;
      return r;
  }


  // 要点: 类中有一个map属性, 此方法并不是静态方法
  public R<T> add(String key, Object value) {
      this.map.put(key, value);
      return this;
  }
}
```

<br>

### 封装方式2:
```java
package com.lantu.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(20000,"success",null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(20000,"success",data);
    }

    public static <T> Result<T> success(T data,String message){
        return new Result<>(20000,message,data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(20000,message,null);
    }

    public static<T>  Result<T> fail(){
        return new Result<>(20001,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> fail( String message){
        return new Result<>(20001,message,null);
    }

}
```

<br>

### 封装方式3: 企业级封装
GWES项目中

<br><br>

## 登录: 功能实现

<br>

### 控制器方法: 参数说明
1. Result里面要存放Employee

2. 前端做登录请求的时候会携带username password请求参数, 我们使用 Employee来承装, 虽然它里面有很多属性

3. 前端发送post请求(数据格式是JSON), 所以我们使用@RequestBody 注解

4. 参数req, 在登录成功后 我们会将 用户id存到session中一份, 这样我们只要想获取当前登录用户的话 可以随时获取到

5. 最终我们要将查询出来的用户记录 返回给前端

```java
// 处理登录请求
@PostMapping("/login")
public Result<Employee> login(@RequestBody Employee employee, HttpServletRequest req) {
  return null;
}
```

<br>

### 逻辑梳理
1. 将页面提交的密码 password 进行 md加密 处理
2. 根据页面提交的用户名 username 查询数据库
3. 如果没有查询到则返回 登录失败 (因为用户名不存在)
4. 密码比对, 如果不一致则返回 登录失败
5. 查看员工状态, 如果为已禁用状态 则返回员工已禁用结果
6. 登录成功, 将员工id存入session并返回成功结果, 结果为查询出来的用户记录

<br>

### md加密的工具类:
org.springframework.util.D

**<font color="#C2185B">DigestUtils.md5DigestAsHex(byte[] bytes)</font>**  
进行md5加密

<br>

### 处理登录请求的逻辑部分:
```java
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
```

<br>

### 测试: 登录功能
我们使用断点的方式来进行验证, 比如我们将断点设置在 控制器方法的方法体的第一行

然后访问 登录页面
```s
http://localhost:8080/backend/page/login/login.html
```

<br>

### 扩展: 数据库存储时间格式的问题
这个项目中时间在数据库中存储的格式如下: 
```
2021-05-06 17:20:07
```

但是我们返回给前端的时候, 它的格式变成了, 带了一个T
```
2021-05-06T17:20:07
```

所以前台在展示的时候 可以进行下格式化
```js
let obj = localStorage.getItem("userInfo")
obj = JSON.parse(obj);

let date = obj.createTime
date.toLocaleString().replace(/T/g, " ").replace(/\.d{3}Z/, "")
```

<br>

### 扩展: 数据库存储时间的格式 和 查询出来的格式
这个项目中时间在数据库中存储的格式如下: 
```
2021-05-06 17:20:07
```

但是我们使用MyBatis来查询数据的时候 查询到的结果却变成了
```
2021-05-06T17:20:07
```

<br>

**原因:**  
这是因为MyBatis会将Mysql中的dateTime类型 转换成 Java中的LocalDateTime类型造成的

这个时候我们就可以添加一个方法 用于进行时间的转换

```java
public Long toTimestamp(LocalDateTime localDateTime) {
  // 通过LocalDateTime.atZone方法，使用系统默认时区ZonId.systemDefault()获取Instant实例
  ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

  Instant instant = zonedDateTime.toInstant();

  // 获取时间戳
  long timeStamp = instant.toEpochMilli(); // 1669392000000

  // 如果想要进行日期格式化    2022-11-26 00:00
  String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time); 

  return timeStamp;
}
```

<br>

注意，这种转换出来的数据，是一个时间戳，具体来说返回来的数据就是1970年到现在查询的时间，距离的毫秒数。如果想要将其转换为年月日，这个时候调用SimpleDateFomat方法，进行日期格式化。

<br>

**ChatGPT给出的答案:**  
这是因为 MyBatis 在将数据库中的 datetime 类型字段映射到 Java 对象时，将其转换为了 Java 中的 java.util.Date 或者 java.time.LocalDateTime 类型。

而这些 Java 类型的默认格式与数据库中的 datetime 类型的格式不同。

**在 java.util.Date 类型中**  
日期和时间都被存储在单个 long 类型变量中，表示自 1970 年 1 月 1 日 00:00:00 GMT 以来的毫秒数。

**在 java.time.LocalDateTime 类型中:**  
则以 ISO 8601 格式表示，即日期和时间之间使用 T 分隔符。

<br>

如果您想在查询结果中使用数据库中的格式，可以在查询语句中使用 DATE_FORMAT 函数将其转换为所需的格式，例如：

这将返回一个名为 formatted_datetime 的列，其中包含格式为 yyyy-MM-dd HH:mm:ss 的日期时间字符串。
```sql
SELECT DATE_FORMAT(datetime_column, '%Y-%m-%d %H:%i:%s') AS formatted_datetime FROM my_table;
```

<br>

**T的含义:**  
在 ISO 8601 格式中，字母 "T" **是日期和时间的分隔符**，用于区分日期和时间。

这个字母代表着 "time"，因此 ISO 8601 中的日期时间格式可以写成这样：
```
YYYY-MM-DDThh:mm:ss
```

需要注意的是，在不同的编程语言和应用程序中，日期时间格式的表示方式可能会略有不同。有些编程语言和库可能使用其他的分隔符，或者可能会包含更多或更少的时间信息。因此，需要根据具体情况来确定日期时间格式的具体表示方式。

<br><br>

# 退出功能

<br>

## 需求分析
员工登录成功后, 页面跳转到后台系统首页页面(backend/index.html) **此时会显示当前登录用户的姓名(userInfo.name)**

如果员工需要退出系统 直接点击右侧的退出按钮即可退出系统, 退出系统后页面应跳转回登录页面

<br>

点击 [退出] 按钮后, 会发起请求 ``/employee/logout`` post请求

<br><br>

## 逻辑分析:
1. 清理session中的用户id
2. 返回结果

<br><br>

## 功能实现:

### 后台逻辑:
1. 前台退出登录发起的是post请求
2. 我们响应回退出登录成功的字样
```java
// 处理退出登录请求
@PostMapping("/logout")
public Result<String> logout(HttpServletRequest req) {

  // 1. 清理session中保存的当前登录员工的id
  req.getSession().removeAttribute("employee");

  // 2. 返回 退出成功 的msg
  return Result.success("退出成功");
}
```

<br>

### 前台逻辑:
自己看下源码 我们这里做文字成熟
1. 拿到后台返回的code进行判断 
2. 如果退出登录成功后 **我们需要删除本地存储中的userInfo**
3. 跳转的指定的页面

<br><br>

## 完善: 登录功能

<br>

### 问题分析:
前面我们已经完成了后台系统的员工登录功能的开发 但是还存在一个问题

用户如果不登录 直接通过url的方式也能进入到后台系统的首页页面, 照样可以访问

<br>

### 解决方式: 
使用 过滤器 或者 拦截器

1. 过滤器: 是web的一个组件 Filter
2. 拦截器: 是SpringMVC为我们提供的拦截器

在过滤器或者拦截器中判断用户是否已经完成登录, 如果没有登录则跳转到登录页面

我们的目的就是拦截用户的请求 来判断用户是否已经登录

<br>

**过滤器:**  
它是在浏览器 和 目标资源之间进行过滤 

<br>

**拦截器:**  
拦截器有3个方法, 它们都是执行在 控制前方法 的前后的

- PreHandle: 控制器方法执行之前执行的
- PostHandle: 控制器方法执行之后执行的
- afterCompletion: 渲染视图后执行的

<br>

本案例中使用过滤器来实现该效果

<br>

### 过滤器逻辑梳理:
**1. 创建自定义过滤器 LoginCheckFilter**

<br>

**2. 在启动类上加入注解 @ServletComponentScan, 开启组件扫描**

当我们在主启动类上使用该注解后 
- Servlet
- Filter
- Listener

如上的组件可以直接通过如下的注解自动注册, 无需其他的代码
- @WebServlet
- @WebFilter
- @WebListener

```java
@SpringBootApplication
@ServletComponentScan
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```

<br>

**3. 完善过滤器的处理逻辑**

<br>

### 过滤器的定义
这里我们看下过滤器如何配置

1. 使用 @WebFilter 注解标识该类

2. 使用 filterName 和 urlPatterns 指明 过滤器名 和 拦截uri

3. 该类要继承 Filter 接口

4. 重写 doFilter() 方法

5. log.info("内容: {}", 变量), 这是Slf4j语法中特有的书写方式 {} 相当于占位符, 变量会被拼接到占位符里面

6. **主启动类上添加 @ServletComponentScan**, 加上后我们就可以使用@WebFilter注解来自动注册过滤器

```java
package com.sam.reggie.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    
    // 强转下 req res 之后会用到
    HttpServletRequest req = (HttpServletRequest) servletRequest;

    HttpServletResponse res = (HttpServletResponse) servletResponse;

    // Slf4j 特有的拼接变量的方式
    log.info("拦截到请求: {}", req.getRequestURI());

    // 放行
    filterChain.doFilter(req, res);
  }
}
```

<br>

### 功能实现:
**过滤器中的逻辑:** 
1. 获取本次请求的uri
2. 判断本次请求是否需要处理, 因为并不是访问所有的请求都需要用户是登录状态
3. 如果不需要处理 则直接放行
4. 判断登录状态, 如果已登录 则直接放行
5. 如果未登录则返回未登录结果

<br>

**拦截目标:**  
我们只拦截对Controller的请求, 其它的页面请求 和 静态资源(css, js)等可以直接放行 页面想看就看 只要看不到数据就可以

<br>

### AntPathMatcher的使用:
路径在很多地方都会使用到, 比如 文件名 url地址

**Spring** 为 PathMatcher 接口提供了一个默认实现 AntPathMatcher，支持 Ant 风格的路径匹配, 它支持 ``? * **``

<br>

**使用场景:**  
路径有通配符的时候, 我们想让路径中的通配符产生作用的情况下 判断两个路径是否能匹配 我们就可以使用它

<br>

**<font color="#C2185B">new AntPathMatcher()</font>**  
实例化路径匹配器

<br>

**<font color="#C2185B">实例对象.match(String pattern, String path)</font>**  
```java
// url: "/backend/**", requestURI: /backend/index.html
boolean match = PATH_MATCHER.match(url, requestURI);
```

<br>

### 要点:
在过滤器中我们没有办法使用@RestController注解给我们提供的便利的功能, 如果在过滤器中我们想 响应前端一个数据 我们需要使用 FastJson + res.getWriter() 方法
```java
String json = JSON.toJSONString(Result.error("NOTLOGIN"));
res.getWriter().write(json);
```

<br>

### 过滤器部分的代码逻辑:
```java
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
      前端的响应拦截器中 会根据code是否为0 和 msg是否为 NOTLOGIN 来判断用户在未登录的情况下需要跳转到登录页(同时会删除本地存储中的userInfo)

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
```

<br>

### 扩展: 测试类的问题
SpringBoot中 测试类所在的包好像要和要测试的类所在的包一致

当我们想定义一个跟项目无关的测试类时需要在 ``@SpringBootTest(classes = FeatureTest.class)`` 注解

表示该测试类就是个普通的测试类

```java
@SpringBootTest(classes = FeatureTest.class)
public class FeatureTest {
  @Test
  public void test01() {
    AntPathMatcher matcher = new AntPathMatcher();
    String url = "/backend/**";
    boolean match = matcher.match(url, "/backend/index.html");
    System.out.println("match = " + match);
  }
}
```

<br><br>

# 功能: 新增员工

## 需求分析:
后台系统重可以管理员工信息, 通过新增员工来添加后台系统用户 点击 [添加员工] 按钮跳转到新增页面

```
账号: _ _ _ _ _
员工姓名: _ _ _ _ _
手机号: _ _ _ _ _
性别: _ _ _ _ _
身份证号: _ _ _ _ _

[取消] [保存] [保存并继续添加]
```

<br>

**username字段:**  
**新增页面录入的员工数据插入到 employee表** 

需要注意的是 employee表中归队username字段加入了唯一约束, 因为username是员工登录的账号 **必须是唯一的**

<br>

**status字段:**  
它的默认值是1, 0表示账号被禁用

<br><br>

## 功能流程分析
1. 页面发送ajax POST请求, 将新增员工页面中输入的数据以JSON的形式提交到服务器 ``/employee``
2. 服务器Controller接收页面提交的数据并调用Service将数据进行保存
3. Service调用Mapper操作数据库, 保存数据

<br><br>

## 功能实现
前端代码是现成的 我们实现Controller部分就可以

<br>

### Controller: save控制器方法
1. 控制器方法上的 ``@PostMapping`` 注解不用指定 url  
因为前台在发起 [添加员工] 的请求的请求地址为: /employee, 由于EmployeeController类上添加了 ``@RequestMapping("/employee")`` 注解 并指定了 前缀 /employee, 所以save控制方法上的 ``@PostMapping``就不用再指定路径了

<br>

2. 控制方法的返回值设置为 Result``<String>``  
前端页面会根据code来判断是否添加员工成功 所以我们返回一个String就可以

<br>

3. 我们使用 Employee实体类 来接收前台发送过来的请求参数(JSON)

<br>

4. 在该Demo中, 我们会给新增用户赋初始密码  
前端新增员工页面form里没有输入初始密码的表单项, 我们在这里给员工设置初始密码

<br>

5. 我们会为Employee实体类中的其他属性赋值, 如 createTime, updateTime 等

<br>

6. createTime的类型是LocalDateTime, 我们在Java层面给它赋值的时候使用的是 ``LocalDateTime.now()`` api, 2023-03-22T21:43:56.549

<br>

```java
// 处理添加员工的请求, 请求路径是 /employee 类上指定了所以我们这里不用写了
@PostMapping
// 因为前端在判断用户是否添加成功的时候 使用的是 code, 所以我们回传给前端的Result里面放个String就可以
public Result<String> save(@RequestBody Employee employee, HttpServletRequest req) {
  log.info("新增员工, 员工信息: {}", employee.toString());

  // password属性: 设置初始密码(md5加密): 前端新增员工页面form里没有输入初始密码的表单项, 我们在这里给员工设置初始密码
  String pwd = DigestUtils.md5DigestAsHex("123456".getBytes());
  employee.setPassword(pwd);

  // status属性: 数据库中有默认值 可忽略

  // createTime属性: LocalDateTime类型
  // 获取当前系统时间(Java端的时间) 2023-03-22T21:43:56.549
  employee.setCreateTime(LocalDateTime.now());

  // updateTime属性: 跟新时间
  employee.setUpdateTime(LocalDateTime.now());

  // createUser: 添加员工的人 也就是当前用户, 传入session中的id
  Long empId = (Long) req.getSession().getAttribute("employee");
  employee.setCreateUser(empId);

  // updateUser: 更新人 也是当前用户
  employee.setUpdateUser(empId);

  log.info("补充employee属性后的对象: {}", employee);

  // 调用service层的方法
  employeeService.save(employee);
  return Result.success("新增员工成功");
}
```

<br><br>

# 全局异常处理器
我们上面的逻辑还有一些不完善的地方, 比如第一次我们添加了一个zhangsan, 第二次我们再添加zhangsan的时候, 由于employee表中的username字段具有唯一约束

所以我们重复添加的时候会抛出下面的异常
```
java.sql.SQLIntegrityConstranintViolationException: Duplicate entry 'zhangsan' for key 'username'
```

<br>

此时对于该异常的捕获 我们有两种方式
1. 在Controller方法中加入 try catch 进行异常捕获
2. 使用异常处理器进行全局异常捕获

<br>

### 解决方式1: try catch
```java
try {
  employeeService.save(employee);
} catch (Exception e) {
  Result.error("新增员工失败");
}

return Result.success("新增员工成功");
```

<br>

### 解决方式2: 全局异常处理器
我们统一进行处理, 不管哪个模块出现异常 我们统一在一个位置进行捕获

**全局异常处理器的配置方式:**  
1. com.sam.reggie.common.GlobalExceptionHandler 创建一个Java类

2. 类上使用 @ControllerAdvice(annotations = {RestController.class, Controller.class}) 注解 进行标识  
将该类标识为一个异常组件, **并使用annotations属性指明拦截 使用了@RestController 和 @Controller 注解的类**  

3. 它是统一处理异常的类 我们的demo中遇到异常后 会向前端响应数据 所以我们在该类上添加 @ResponseBody 注解, 最终将JSON数据进行返回

4. 类中定义处理 指定异常的方法, 方法上使用 @ExceptionHandler(SQLIntegrityConstraintViolationException.class) 注解 来指定该方法处理哪个异常, 处理方法中要声明该异常类型的形参(SQLIntegrityConstraintViolationException ex)

5. api: ex.getMessage() 就是异常信息

6. 我们要处理的逻辑, 如果是违反了唯一约束的异常 我们向前端返回 哪个字段违反了唯一约束 如 "zhangsan 已存在", 其它异常的话我们返回 未知错误

```java
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
```

<br>

### 扩展: SQLIntegrityConstraintViolationException
SQLIntegrityConstraintViolationException 是一个 Java 异常类，它表示当试图在数据库中插入、更新或删除数据时违反了某些完整性约束条件，例如主键、唯一键或外键限制，导致操作失败的异常。

具体来说，当你尝试在数据库表中插入一条记录，而该记录的某些列值与表中已有记录的某些列值重复时，就会抛出 SQLIntegrityConstraintViolationException 异常。同样地，如果你试图更新或删除表中的某个记录，而这个记录被其他表所引用，或者它是一个主键或唯一键，就会抛出这个异常。

当你捕获 SQLIntegrityConstraintViolationException 异常时，通常可以从异常消息或根本原因中获取更多信息，例如具体的违反约束的列和值，从而可以进一步调试和修复你的代码逻辑或数据库架构。

<br>

SQLIntegrityConstraintViolationException里面包含了很多sql的异常异常 不一定是我们想要捕捉的 

比如我们demo中就是想要捕获 唯一约束的问题 所以我们对异常信息进行了字符串判断 和 截取

如果是唯一约束的异常会有 Duplicate entry 字样

```
java.sql.SQLIntegrityConstranintViolationException: Duplicate entry 'zhangsan' for key 'username'
```

<br><br>

# 员工信息分页查询
员工管理页面的数据列表

<br>

## 需求分析

### 展示列表: 分页处理
系统中的员工如果在一个页面中全部展示出来会显得比较乱, 不便于查看, **所以一般的系统中会以分页的方式来展示列表数据**

<br>

### 员工查询: 分页处理
可能我们查询到的员工太多, 所以在查询的时候我们也需要做分页的处理

<br><br>

## 梳理程序的执行过程
1. 页面发送ajax请求, 将分页需要的查询参数(page, pageSize, name) 提交到服务器, 如果我们没有输入name的值 它会是undefined  
axios在发送请求的时候 会将请求参数进行JSON.stringify()进行处理, 如果name的值为undefined, 相当于该次请求没有携带name参数, name参数会被忽略掉

```js
// 分页查询 / 查询用户
{
  page: 1,
  pageSize: 10,
  name: 张三 | undefined
}
```

2. 服务端Controller接收页面提交的数据 并调用service查询数据

3. service调用mapper操作数据库, 查询分页数据

4. Controller将查询到的分页数据响应给页面

5. 页面接收到分页数据并通过ElementUI的Table组件展示到页面上

<br>

### 前端页面相关逻辑
后台管理页面会在 created 周期中 调用 init() 方法, 该方法中会发送 get请求, 请求地址: /employee/page

<br>

**要点:**  
1. 当name的值为undefined的时候, axios在发送请求时 会忽略name参数, 详情就是JSON.stringify会忽略undefined类型的参数

2. 前端需要在data中获取如下的数据, 所以我们在返回数据的时候也需要组织好这些数据
```js
data: {
  code: "",
  records: "",
  records: "",
  total: ""
}
```

```js
async init () {
  // 整理请求参数
  const params = {
    page: this.page,
    pageSize: this.pageSize,
    name: this.input ? this.input : undefined
  }

  // 调用接口, 判断 code
  await getMemberList(params).then(res => {
    if (String(res.code) === '1') {
      this.tableData = res.data.records || []
      this.counts = res.data.total
    }
  }).catch(err => {
    this.$message.error('请求出错了：' + err)
  })
}
```

<br><br>

## 逻辑实现
分页查询会使用MyBatis-Plus提供的分页插件 它可以简化分页查询的代码量

<br>

### 分页插件的配置
```java
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

```

<br><br>

## Controller层: 创建控制器方法
该方法用于接收前端发送的分页请求, 从前端获取 page, pageSize, name 等参数

前端页面有两种情况会发送请求:

<br>

**1. 前端页面刷新:**  
这时前端会发起 /employee/page get请求, 请求分页数据 会携带如下的参数
1. page
2. pageSize

<br>

**2. 员工管理页面, 输入员工姓名 点击查询按钮**  
这时前端会发起 /employee/page get请求, 在按照name搜索的同时 请求分页数据 会携带如下的参数
1. page
2. pageSize
3. name

<br>

page 和 pageSize 有默认值 为1, 10

<br>

### 要点:
**1. 控制前方法的返回值泛型 我们传入什么?**  
Result``<Page<Employee>>``, 泛型不是想传递什么就是什么 我们需要查看前端需要什么数据

比如Demo中其阿奴单需要的是数据是 code, records, total 所以我们不能放Employee

我们要传入Page对象, 该对象中封装着records 和 total 等分页相关的呃数据

<br> 

**2. 控制器方法中形参接收前端参数**  
前端发起请求是通过url携带了 page pageSize name等参数 所以我们直接在形参中接收参数就可以

前端如果没有传递name的值, 那么Java端在接收的时候name的值就是null

<br>

**3. 返回分页数据**  
返回分页数据 我们使用的是 employeeService.page() 方法 该方法需要两个参数
1. page对象
2. queryWrapper对象

所以在使用该方法前我们需要创建这两个对象

<br>

**要点:**  
我们前端有可能会传进来name参数, 如果传入进行我们就需要在sql中拼接 name like "条件" 

如果没有传入则不拼接到sql中, 这里我们可以使用queryWrapper对象中带有 condition 参数的方法 

当满足该条件的时候 再往sql中拼接改字段信息

<br>

**要点2:**  
- org.apache.commons.lang.StringUtils
- com.baomidou.mybatisplus.core.toolkit.StringUtils;

上面的两个包中都有判断参数是否为空的方法 我们可以使用

<br>

**要点3:**  
``employeeService.page(pageInfo, queryWrapper);``方法不需要创建返回值接收

```java
// 处理分页请求的方法
// Result<>的泛型中要传什么? 我们前端需要的数据是 code, records, total, 所以我们不能放Employee, 因为Employee中没有这些属性
// 我们要传入Page对象, 该对象中封装着 records 和 total 等分页相关的数据
// 泛型不是随便来的 它需要跟页面配合 页面需要什么数据 我们就传入什么数据
// 形参的参数: page: 1, pageSize: 10, name: undefined
@GetMapping("/page")
public Result<Page<Employee>> page(int page, int pageSize, String name) {

  // 1. 构造分页构造器: 告诉MyBatis我们要查询哪页 查询几条
  Page pageInfo = new Page(page, pageSize);

  // 2. 构造条件构造器: 如果传递了name 就要拼接到sql中
  LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
  // org.apache.commons.lang.StringUtils isNotEmpty
  queryWrapper.like(StringUtils.isNotBlank(name), Employee::getName, name);

  // 添加排序条件: 根据更新时间来进行排序
  queryWrapper.orderByDesc(Employee::getUpdateTime);


  // 3. 执行查询: employeeService.page()方法不需要返回值, 当它查询完后 会直接将数据封装到pageInfo对象中 我们不用再次接受返回值
  employeeService.page(pageInfo, queryWrapper);

  return Result.success(pageInfo);
}
```

<br>

### 前端接收到的数据格式:
上面我们将 Page对象 响应回了前端, page对象中封装了分页数据 我们看看返回数据的格式是什么样的

我们能看到 page对象就是data变量的右边的部分

```js
{
  "code": 1,
  "msg": null,
  "data": {
    "records": [
      {
        "id": 1638529636642578434,
        "username": "lisi",
        "name": "李四",
        "password": "e10adc3949ba59abbe56e057f20f883e",
        "phone": "13888888888",
        "sex": "1",
        "idNumber": "202009101000110909",
        "status": 1,
        "createTime": "2023-03-22T22:14:42",
        "updateTime": "2023-03-22T22:14:42",
        "createUser": 1,
        "updateUser": 1
      }
    ],
    "total": 3,
    "size": 10,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "hitCount": false,
    "countId": null,
    "maxLimit": null,
    "searchCount": true,
    "pages": 1
  },
  "map": {}
}
```

<br><br>

# 启动/禁用 员工账号

## 需求分析
在员工管理列表页面 可以对某个员工账号进行启动或者禁用操作

1. 账号禁用的员工不能登录系统
2. 账号正常的员工可以登录系统

<br>

### 注意:
只有管理员(admin用户)可以对其它普通用户进行启用 禁用等操作 所以普通用户登录系统后启动 禁用按钮不显示

<br><br>

## 前端逻辑
前端在展示页面的时候 如果是管理员身份 会有启动 和 禁用的按钮 这里是怎么做到的？

用户在登录成功后 会往localstorage里面存放该用户在employee表中的信息

其中username就是登录系统的账号, 我们从localstorage将userInfo取出来 查看 username 这个属性 根据这个属性是否是admin来展示对应的结构

<br><br>

## 梳理执行过程
1. 页面发送ajax请求 将参数 (id, status) 提交到服务器
2. 服务端Controller接收页面提交的数据 并调用service更新数据
3. service调用mapper操作数据库

<br>

### 请求信息
前端在点击 启用/禁用 按钮时会发起请求
- 请求地址: /employee
- 请求方式: put
- 请求参数: id and status

<br><br>

## 后台逻辑:
我们在controller类中 定义 update()方法 来处理
- 启用/禁用 员工状态
- 编辑 员工信息

这里是一个控制器方法来处理两种请求, 因为两种请求都是对数据的修改操作 所以可以放在一个控制器方法中

<br>

### 要点:
1. 接收前端请求的参数 我们使用 Employee 对象来接收

2. 前端发起的是put请求, 接收参数的时候我们需要使用 
@RequestBody注解

3. Employee对象中没有赋值的属性会是null, 如果是null我们做更新的时候该属性对应的字段是不会被修改的

4. 我们在做更新操作的时候, 需要设置Employee实体类中的 updatTime 和 updateUser 属性的值, 设置更新时间 和 更新人

<br>

### 代码部分:
```java
// 根据员工id修改员工的信息
@PutMapping
// 返回值泛型: 前端页面需要用到code判断 不需要其它的数据
public Result<String> update(Employee employee, HttpServletRequest req) {

  // 要点: 在做更新操作的时候 我们需要为employee对象中的updateTime updateUser 这两个属性进行赋值 更新时间 和 更新人
  employee.setUpdateTime(LocalDateTime.now());

  Long id = (Long) req.getSession().getAttribute("employee");
  employee.setUpdateUser(id);

  // 调用service修改数据库数据
  employeeService.updateById(employee);

  return Result.success("员工信息修改成功");
}
```

<br>

### 问题: 雪花算法带来的问题  
我们上面的代码 没有报错 但是修改用户数据缺失败了

<br>

上面我们是根据用户的id做为sql的更新条件, 找到id指向的用户 修改该用户的信息

```sql
update employee set status = ?, update_time = ?, update_user = ? where id = ?
```

<br>

但是 id的值是由雪花算法生成的 它是一个Long类型 **19位长度**的数字
```s
# 前端 localstorage 里面存储的 李四的id为:
1638529636642578400


# 数据库 里面存储的 李四的id为
1638529636642578434
```

我们发现前后台的id值不一致, **问题出在js**

<br>

页面在**js处理long型数字只能精确到16位**, js只能保证前16位, 16位之后的它做了四舍五入的处理 

所以最终通过ajax请求提交给服务端的时候id变了

<br>

所以id既然错了, 那么当我们根据前端传送过来的id进行修改数据库的时候 就会导致匹配不上数据而修改失败

<br>

### 解决方式: 
我们可以在服务器端给页面响应json数据的时候 统一进行处理, **将long型数据统一转为字符串**

<br>

**实现步骤:**  
我们需要在配置类中扩展一个**消息转换器**, 在消息转换器中在对Java对象转成Json的时候统一进行处理

具体处理的时候又会调用**对象转换器**, 对象转换器的底层会使用jackson

<br>

我们需要进行如下的两步操作

<br>

1. 提供**对象转换器** jacksonObjectMapper, 基于jackson进行java对象到json数据的转换

2. 在WebMvcConfig配置类中扩展SpringMVC
的**消息转换器**, 在此消息转换器中使用提供的对象转换器进行java对象到json数据的转换

<br>

**步骤1: 创建 对象转换器:**  
这个类是老师提供的 我们放在了common包下, 将我们java对象转成json 在转换的过程中 我们对各种各样的数据形式做了处理

这里我们主要是将long型的数字 转换为字符串 这样就不会出现丢失精度的问题了

```java
package com.sam.reggie.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


/*
  对象映射器:
    基于jackson将Java对象转为json，或者将json转为Java对象
    将JSON解析为Java对象的过程称为 [从JSON反序列化Java对象]
    从Java对象生成JSON的过程称为 [序列化Java对象到JSON]
*/
public class JacksonObjectMapper extends ObjectMapper {

  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
  public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

  public JacksonObjectMapper() {
    super();
    //收到未知属性时不报异常
    this.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

    //反序列化时，属性不存在的兼容处理
    this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


    SimpleModule simpleModule = new SimpleModule()
        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
        .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
        .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))

        .addSerializer(BigInteger.class, ToStringSerializer.instance)
        // 对于lang数据进行处理的时候 会使用ToStringSerializer序列化器, 作用: 将lang型数据转换为字符串
        .addSerializer(Long.class, ToStringSerializer.instance)
        // 对日期时间类型进行序列化 将其转换成响应的字符串 yyyy-MM-dd or yyyy-MM-dd HH:mm:ss or HH:mm:ss 
        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
        .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
        .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

    //注册功能模块 例如，可以添加自定义序列化器和反序列化器
    this.registerModule(simpleModule);
  }
}
```
 
<br>

**步骤2: 在WebMvcConfig配置类中扩展SpringMVC的消息转换器:**  
如果我们不配置就是使用的默认的消息转换器, 现在我们要对它进行扩展
```s
# MVC框架默认的消息转换器 一种有8个默认的转换器
- ByteArrayHttpMessageConverter
- StringHttpMessageConverter
- ResourceHttpMessageConverter
- ResourceRegionHttpMessageConverter
- SourceHttpMessageConverter
- AllEncompassingFormHttpMessageConverter
- Jaxb2RootElementHttpMessageConverter
- MappingJackson2HttpMessageConverter
```

<br>

在扩展的同时我们在消息转换器中使用上面创建的对象转换器, **配置类中的配置会在项目启动的时候生效**

<br>

```java
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

  // 设置 请求资源 映射到 哪个目录下 addResourceHandler资源处理器, 主要路径中 backend 就会映射到
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
  }




  // 重写extendMessageConverters方法: 扩展mvc框架的消息转换器
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    
    /*
      创建消息转换器对象: 

        将controller中返回的结果 转成 相对应的json, 再通过输出流响应给页面, 前端收到的json数据就是通过消息转换器传输的
    */
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

    // 设置自己配置的对象转换器
    messageConverter.setObjectMapper(new JacksonObjectMapper());

    // 将消息转换器对象追加到mvc框架中的转换器集合中, 参数1是索引, 表示转换器的顺序, 将我们自己设置的消息转换器设置为首位
    converters.add(0, messageConverter);
  }
}
```

<br><br>




### 扩展:
id是雪花算法得到的值 但是传递到前端的时候 可能会造成精度丢失

如果有精度丢失的问题 可以在实体类的id属性上添加 ``@JsonFormat(shape=JsonFormat.Shape.STRING)`` 注解

