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

<br>

### 扩展:
id是雪花算法传到前端, 前端提交数据时的精度损失问题可以参考下面的方式

在实体类的id属性上添加 ``@JsonFormat(shape=JsonFormat.Shape.STRING)`` 注解

<br><br>

# 编辑员工信息

## 需求分析
在员工管理列表页面点击编辑按钮, 跳转到编辑页面 **在编辑页面回显员工信息并进行修改**, 最后点击保存按钮完成编辑操作

<br><br>

## 梳理逻辑

### 思考:
在列表中的一行内 点击编辑按钮, 不可以用scope参数 传递到编辑页面么?

<br>

### Demo逻辑
1. 点击编辑按钮时, 页面跳转到 add.html 并**在url中携带员工参数(员工id)**

2. **在add.html页面获取url中的参数(员工id)**

3. **发送ajax请求, 请求服务端, 同时提交员工id参数**

4. 服务端接收请求, **根据员工id查询员工信息**, 将员工信息以JSON形式响应给页面

5. 页面接受服务器响应的json数据, 通过vue的数据绑定进行员工信息的回显

6. 点击保存按钮, 发送ajax请求, 将页面中的员工信息以json方式提交给服务器

7. 服务器端接收员工信息 并进行处理, 完成后给页面响应

8. 页面接收到服务端响应信息后进行相应的处理

<br>

**注意:**  
**add.html页面为公共页面** 新增员工 和 编辑员工 都是在此页面操作

<br>

### 页面动作
这里跟上面的Demo逻辑搭配着看

**1. 员工列表页面点击 编辑按钮 后**  
会跳转到add.html页面 并携带id
```s
localhost:8080/backend/page/member/add.html?id=68246582765
```

<br>

页面在点击 [添加员工] 的时候 会执行 addMemberHandle("add")回调中会传入一个 add的标识符

回调内会对add标识符进行判断, 如果没有 add标识符跳转页面的时候就是修改 并携带id参数
```js
addMemberHandle (st) {
  if (st === 'add'){
    window.parent.menuHandle({
      id: '2',
      url: '/backend/page/member/add.html',
      name: '添加员工'
    },true)
  } else {
    window.parent.menuHandle({
      id: '2',
      url: '/backend/page/member/add.html?id='+st, 
      name: '修改员工'
    },true)
  }
},
```

<br>

2. **在add.html页面获取url中的参数(员工id) 并发送ajax请求**  
```s
请求地址: /employee/68246582765
请求方式: get
```

<br>

add页面在create周期中 会获取url上的id参数 然后根据是否有id参数来进行判断 是修改 还是 添加

```js
created() {
  this.id = requestUrlParam('id')
  this.actionType = this.id ? 'edit' : 'add'
  if (this.id) {
    this.init()
  }
}
```

<br>

如果是修改功能则发起 init 请求, 获取该id员工的数据 用户页面的回显
```js
async init () {
  queryEmployeeById(this.id).then(res => {
    console.log(res)
    if (String(res.code) === '1') {
      console.log(res.data)
      this.ruleForm = res.data
      this.ruleForm.sex = res.data.sex === '0' ? '女' : '男'
      // this.ruleForm.password = ''
    } else {
      this.$message.error(res.msg || '操作失败')
    }
  })
},
```

<br>

**3. 用户修改完用户信息点击保存按钮 再次发送ajax请求 将json方式提交给服务器端**  
修改用户数据的话 应该打向 update的控制器方法

<br><br>

## 功能实现:

### 处理根据id查询对应的用户信息的控制器方法:
1. 前端发起的请求是restful风格的接口, 所以我们要使用{}路径占位符 和 @PathVariable 注解

2. 对查询到的数据进行判空处理

```java
@GetMapping("/{id}")
  public Result<Employee> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeService.getById(id);

    if(employee != null) {
      return Result.success(employee);
    }
    return Result.error("没有查询到对应的员工信息");
  }
```

<br>

点击 [保存] 按钮, 发送请求 保存修改后的数据到数据库的功能已经开发完毕了 就是update()控制器方法

<br><br>

# 分类管理业务
上面是员工管理模块 这里我们要实现分类管理模块

我们在分类管理模块可以添加分类
1. 新增 菜品分类
2. 新增 套餐分类

<br>

后续还会有 如下的两个模块
1. 菜品管理 模块
2. 套餐管理 模块

当我们添加一个菜品的时候, 它必须要对应一种菜品的分类, 所以我们要提前将 分类管理模块 创建出来

<br><br>

## 公共字段自动填充
公共字段自动填充是由MyBatis-Plus框架给我们提供的一种功能 **该功能可以将公共的字段交给MyBatis-Plus来维护** 可以简化我们的开发

<br>

### 问题分析
前面我们已经开发的代码会存在如下的问题
1. 在新增员工时 需要设置 创建时间, 创建人, 修改时间, 修改人等字段

2. 在编辑员工时 需要设置 修改时间, 修改人等字段

```
create_time  datetime
update_time  datetime
create_user  bigint
update_user  bigint
```

<br>

类似这样的字段属于公共字段 也就是很多表中都有这些字段, 那能不能对这些公共字段在某个地方统一处理 来简化开发呢

答案就是用MyBatis-Plus提供的 **公共字段自动填充** 功能

<br>

### 公共字段自动填充: 代码实现
MyBatisPlus的公共字段自动填充 也就是**在插入或者更新的时候为指定字段赋予指定的值**

使用它的好处就是可以对这些字段进行处理 避免了重复代码

<br>

### 实现步骤:
1. 在实体类的属性上加入 **@TableField** 注解, 指定自动填充的策略
- DEFAULT: 默认不处理
- INSERT: 插入时 填充字段
- UPDATE: 更新时 填充字段
- INSERT_UPDATE: 插入 和 更新时 填充字段

<br>

2. **按照框架要求编写元数据对象处理器**, 在此类中统一为公共字段赋值, 此类需要实现MetaObjectHandler接口

<br>

### 步骤1:
```java
@Data
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String username;
  private String name;
  private String password;
  private String phone;
  private String sex;
  private String idNumber;//身份证号码
  private Integer status;

  // 插入时 填充字段
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  // 插入 和 更新时 填充字段
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  // // 插入时 填充字段
  @TableField(fill = FieldFill.INSERT)
  private Long createUser;

  // 插入 和 更新时 填充字段
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;

}
```

<br>

### 步骤2:
创建一个类(类名任意) **主要是需要实现 MetaObjectHandler接口**  

<br>

**要点:**  
**1. MetaObjectHandler实现类中要实现如下的两个抽象方法**
- insertFill(MetaObject metaObject)
- updateFill(MetaObject metaObject)

如上的两个方法会在
- 当执行 insert语句的时候 会调用 insertFill
- 当执行 update语句的时候 会调用 updateFill

<br>

**2. MetaObject类型形参**  
它里面封装着 我们要操作的对象, 比如我们插入员工数据的时候, 它里面封装的就是Employee实体类

```js
metaObject参数: 元数据对象
{
  originalObject: 它里面是 employee对象
  objectWrapper:
  objectFactory:
  objectWrapperFactory
  reflectorFactory:
}
```

<br>

**3. metaObject.setValue("实体类中属性名", "值")**  
我们通过setValue方法 为实体类中的属性(对应着表中的字段) 赋值

这些字段就是交给MyBatis-Plus管理的公共字段

```java
package com.sam.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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
    System.out.println("metaObject = " + metaObject.toString());
    metaObject.setValue("createTime", LocalDateTime.now());
    metaObject.setValue("updateTime", LocalDateTime.now());
    // createUser需要添写 当前用户的Long id, 我们在控制层的时候当前用户的id是从session中获取的 但是我们在当前类中获取不到session对象 暂时我们先写死
    metaObject.setValue("createUser", new Long(1));
    metaObject.setValue("updateUser", new Long(1));
  }

  // 当执行 update语句的时候 会调用该方法
  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("公共字段自动填充 [update]");
    System.out.println("metaObject = " + metaObject.toString());
    metaObject.setValue("updateTime", LocalDateTime.now());
    metaObject.setValue("updateUser", new Long(1));
  }
}

```

<br>

**自动填充的优势:**   
它不仅仅是针对Employee表的 后续的其他的表(实体类中有这些属性) 都会被自动填充

<br><br>

## 功能完善: 在元数据对象处理器中动态获取登录用户id
前面我们已经完成了公共字段自动填充功能的代码开发

但是还有一个问题没有解决, 就是我们在自动填充createUser 和 updateUser 的时候设置的用户id是固定值

**现在我们需要改成动态获取当前登录用户的id**  

但是 元数据对象处理器 中无法获取到 HttpSession 对象, 所以我们考虑使用 **ThreadLocal** 来解决该问题

<br>

### 弹幕扩展:
他们说不用 ThreadLocal 也可以 可以使用如下的方案
1. @Autowired HttpSession session
2. @Autowired HttpServletRequest request

测试了 确实可以

<br>

### ThreadLocal
ThreadLocal**可以给当前线程关联一个数据**, 这样就避免其它的线程访问这个数据

也就是说 可以给当前线程 关联一个数据, 我们可以根据当前线程名 来获取 和 设置该数据
```java
{
  当前线程: 关联的数据
}
```

简单理解: 它可以像Map一样存取数据 key为当前线程

<br>

**回顾使用方式:**  
```java
public class ThreadData {

  public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();


  // 在哪个逻辑中保存数据
  threadLocal.set(i);
}


// 在别的类中获取数据
Object o = ThreadData.threadLocal.get();
```

<br>

### 知识前置:
在使用 ThreadLocal 之前, 我们要知道**客户端发送的每次Http请求 对应的在服务器端都会分配一个新的线程来处理**, 在处理过程中就会涉及到下面类中的方法都属于相同的线程

(可能涉及到多个类的多个调用方法 整个的调用链条 都属于同一个线程)

<br>

1. LoginCheckFilter 的 doFilter 方法
2. EmployeeController 的 update 方法
3. MyMetaObjectHandler 的 updateFill 方法

<br>

**验证上述的3个方法是否为同一个线程**
可以在上面的三个方法中分别加入下面的代码(获取当前线程的id)
```java
Long id = Thread.currentThread().getId();
log.info("线程id: {}", id)

// 输出结果: 上面的三个方法的线程id都是一致的
```

说明客户端发起的一次请求 会经过上面的方法进行处理, 而它们都属于同一个线程内的逻辑

<br>

**总结:**  
一次请求中 上述的三个方法属于一个线程中的

<br>

### TheadLocal介绍
ThreadLocal并不是Thread 而是Thread的局部变量

当使用ThreadLocal维护变量的时候, ThreadLocal为每个使用该变量的线程提供独立的变量副本

**所以每一个线程都可以独立地改变自己的副本, 而不会影响其它的线程所对应的副本**

<br>

ThreadLocal为每个线程提供单独一份存储空间 具有线程隔离效果, 只有在线程内才能获取到对应的值 线程外则不能方法, **它就相当于一个线程中的 vuex**

<br>

**常用方法:**  
- public void set(T value) 设置当前线程的局部变量的值
- public T get() 返回当前线程对应的线程局部变量的值
- public void remove()

<br>

**逻辑:**  
我们可以在LoginCheckFilter的doFilter方法中 获取当前登录用户的id

并调用ThreadLocal的set方法来设置当前线程的线程局部变量的值

然后再MyMetaObjectHandler的updateFill方法中调用ThreadLocal的get方法获取当前线程所对应的线程局部变量的值

<br>

### 代码实现:
1. 编写BaseContext工具类, 基于ThreadLocal封装的工具类

2. 在LoginCheckFilter的doFilter方法中调用 BaseContext来**设置**当前登录的用户id

3. 在MyMetaObjectHandler的方法中调用BaseContext来**获取**当前登录的用户id

<br>

**BaseContext工具类:**  
```java
// 基于ThreadLocal封装的工具类 用于保存和获取当前登录用户的id
public class BaseContext {
  private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

  public static Long getCurrentId() {
    return threadLocal.get();
  }

  public static void setCurrentId(Long id) {
    threadLocal.set(id);
  }
}
```

<br>

**LoginCheckFilter设置用户id:**  
都是通过工具类来操作的
```java
Long empId = (Long) req.getSession().getAttribute("employee");
if(empId != null) {
  // 将用户id保存到ThreadLoal中
  BaseContext.setCurrentId(empId);
  filterChain.doFilter(req, res);
  return;
}
```

<br>

**MyMetaObjectHandler元数据对象处理器中通过ThreadLocal获取id**  
都是通过工具类来操作的
```java
@Override
public void updateFill(MetaObject metaObject) {
  log.info("公共字段自动填充 [update]");

  Long currentId = BaseContext.getCurrentId();

  metaObject.setValue("updateTime", LocalDateTime.now());

  metaObject.setValue("updateUser", currentId);
}
```

<br><br>

# 分类管理: 新增分类

## 需求分析:
后台系统中可以管理分类信息 分类包括两种类型 分别是
- 菜品分类 
- 套餐分类

<br>

### 添加分类的作用
当我们在后台系统中添加菜品的时候需要选择一个菜品分类  
当我们在后台系统中添加套餐的时候需要选择一个套餐分类

在移动端也会按照 菜品分类 和 套餐分类 来展示对应的菜品和套餐 (手机端 左侧菜单栏)

<br><br>

## 数据模型
分类管理 对应 category 数据表 

**数据表category的字段:**  
- id bigint
- type int: 取值1-菜品分类 取值2-套餐分类
- name varchar: 分类名称 具有唯一约束
- sort int
- create_time
- update_time
- create_user
- update_user

<br>

### 准备工作
1. 创建 实体类 Category
2. Mapper接口
3. 业务层接口 和 实现类
4. 控制层

<br>

### category数据表对应的实体类
```java
package com.sam.reggie.entity;

@Data
public class Category implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;


  //类型 1 菜品分类 2 套餐分类
  private Integer type;


  //分类名称
  private String name;


  //顺序
  private Integer sort;


  //创建时间
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;


  //更新时间
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;


  //创建人
  @TableField(fill = FieldFill.INSERT)
  private Long createUser;


  //修改人
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;


  //是否删除
  private Integer isDeleted;

}
```

<br>

### 新增分类 梳理执行流程:
前端页面点击 [新增菜品分类] [新增套餐分类] 按钮 会发起请求 请求地址都是一个 /category

不管是菜品还是套餐都会将数据插入到 category 表中

<br>

**form内容:**
```
分类名称: _ _ _ _ _ 
排序: _ _ _ _ _ 
```

<br>

1. 页面(backend/page/category/list.html) 发送ajax请求 将新增分类创建输入的数据以json形式提交到服务器

2. 服务器端controller接收页面提交的数据并调用service将数据进行保存

3. service调用mapper操作数据库 保存数据

<br>

**新增菜品分类 请求参数:**  
- 请求方式: post
- 请求地址: /category
- 参数:
```js
{
  name: "川菜",
  type: "1",
  sort: "2"
}
```

<br>

**新增套餐分类 请求参数:**  
- 请求方式: post
- 请求地址: /category
- 参数:
```js
{
  name: "周末超值套餐",
  type: "2",
  sort: "1"
}
```

<br>

可以看到新增菜品分类 和 新增套餐分类 请求的服务器地址 和 提交的json数据结构都是相同的

所以服务器只需要提供一个方法统一处理就可以

<br>

### 控制方法:
1. 前端页面只使用了code 所以Result``<String>`` 传入string就可以

```java
@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public Result<String> save(@RequestBody Category category) {
    categoryService.save(category);
    return Result.success("新增分类成功");
  }
}
```

<br><br>

# 分类信息分页查询

## 需求分析
分类管理的列表页面 要求以分页的功能来展示数据

1. 前端页面发送ajax请求 将分页查询参数(page, pageSize)提交到服务端

2. 服务器端controller接收页面提交的数据并调用service查询数据

<br><br>

## 前端相关:
1. 页面在 created 中会调用 init()方法
2. init方法中会发送ajax请求
  - 请求地址: /category/page
  - 请求方法: get
  - 请求参数: page pageSize

3. init方法中会根据返回的code 来执行相关的逻辑

<br><br>

## 后台控制层:
1. 前端会通过 url带参的方式 携带参数 我们直接定义形参来接收前端发送的参数

2. 控制器方法需要返回分页相关的数据, 返回值定义为Page对象

3. LambdaQueryWrapper定义排序sql
```java
@GetMapping("/page")
public Result<Page<Category>> page(Integer page, Integer pageSize) {

  // page对象
  Page<Category> pageInfo = new Page<>(page, pageSize);

  // 条件构造器 设置升序排序
  LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

  queryWrapper.orderByAsc(Category::getSort);

  // 调用service层方法操作数据库
  categoryService.page(pageInfo, queryWrapper);

  return Result.success(pageInfo);
}
```

<br>

### 扩展:
时间戳类型可以在实体类的有关时间上加上如下的注解
```java
@JsonFormat(pattern="yyyy-MM-dd")
```

<br><br>

# 删除分类
在分类管理列表页面 可以对某个分类进行删除操作, 列表中的一行 都会有 [修改] 和 [删除] 两个按钮

**注意:**  
需要注意的是当分类关联了菜品或者套餐的时候 **此分类不允许被删除**

这里我们就需要判断 看看当前的分类下面有没有具体的菜品或者套餐, 如果关联了 则该分类不能删除 并发送提示信息 让前端提示用户

<br>

## 梳理逻辑
1. 页面发送删除的ajax请求 将参数id提交到服务器

2. 服务端Controller接收页面提交的参数id, 在删除分类之前 要检查该分类是否关联了菜品 
  - 如果没有关联 则调用 service删除数据 
  - 如果已经关联 则 不能删除 (抛出业务异常)

3. service调用mapper操作数据库
4. 前端根据返回的结果的code 来提示用户是否删除成功

<br>

### 请求参数
- 请求地址: /category?id=xxxx
- 请求方式: delete

<br>

### 准备工作
因为我们要在删除分类的时候 判断该菜品分类下有没有关联其他的菜品或者套餐 所以我们需要操作其他的表

既然是其他的表那么就需要定义该表的实体类 Mapper Service

- Dish(菜品实体类) 和 Setmeal(套餐实体类)
- DishMapper 和 SetmealMapper
- DishService 和 SetmealService
- DishServiceImpl 和 SetmealServiceImpl

<br>

**Dish实体类:**  
```java
package com.sam.reggie.entity;

/**
 菜品
 */
@Data
public class Dish implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;


  //菜品名称
  private String name;


  //菜品分类id
  private Long categoryId;


  //菜品价格
  private BigDecimal price;


  //商品码
  private String code;


  //图片
  private String image;


  //描述信息
  private String description;


  //0 停售 1 起售
  private Integer status;


  //顺序
  private Integer sort;


  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;


  @TableField(fill = FieldFill.INSERT)
  private Long createUser;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;


  //是否删除
  private Integer isDeleted;

}

```

<br>

**Setmeal:**  
```java
package com.sam.reggie.entity;

/**
 * 套餐
 */
@Data
public class Setmeal implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;


  //分类id
  private Long categoryId;


  //套餐名称
  private String name;


  //套餐价格
  private BigDecimal price;


  //状态 0:停用 1:启用
  private Integer status;


  //编码
  private String code;


  //描述信息
  private String description;


  //图片
  private String image;


  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;


  @TableField(fill = FieldFill.INSERT)
  private Long createUser;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;


  //是否删除
  private Integer isDeleted;
}

```

<br>

### 根据前端传递的id 进行判断
我们要判断当前分类下是否关联着菜品和套餐

这里我们就需要使用 Service接口 扩展我们自己的逻辑, 我们判断的逻辑 放在 CategoryServiceImpl 下进行

<br>

**CategoryService:**  
扩展我们自己的业务层逻辑, controller层接收到前端参数后 调用Service层的remove方法 在Service层的remove方法里面 

做相关的逻辑判断

```java
package com.sam.reggie.service;

public interface CategoryService extends IService<Category> {

  // 扩展的自己的业务逻辑
  public void remove(Long id);
}

```

<br>

**CategoryServiceImpl:**  
我们在Service的实现类中 做具体的业务判断

**逻辑:**  
1. 查询当前分类是否关联了**菜品** 如果已经关联 **抛出一个业务异常**, 需要自定义异常类

2. 查询当前分类是否关联了**套餐** 如果已经关联 **抛出一个业务异常**, 需要自定义异常类

3. 如果上面都没有关联 则正常删除

<br>

其实就是拿着分类的id去菜品表 和 套餐表中进行查询 看看有没有结果 如果有则说明已经关联了

其实就是**通过 dishService 和 setmealService** 中定义的方法分别查询菜品表 和 套餐表

```sql
select count(*) from dish where category_id = ?

select count(*) from setmeal where category_id = ?


setmealService.count(条件构造器)
dishService.count(条件构造器)
```

<br>

```java
package com.sam.reggie.service.impl;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

  @Autowired
  private DishService dishService;

  @Autowired
  private SetmealService setmealService;


  // 根据id删除分类 删除之前 需要进行判断
  @Override
  public void remove(Long id) {

    // 查询当前分类是否关联了菜品 如果已经关联 抛出一个业务异常
    // 查询dish表: select count(*) from dish where category_id = ?
    LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

    dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);

    int dishCount = dishService.count(dishLambdaQueryWrapper);


    if(dishCount > 0) {
      // 已经关联菜品 抛出一个业务异常
    }


    // 查询当前分类是否关联了套餐 如果已经关联 抛出一个业务异常
    // 查询dish表: select count(*) from setmeal where category_id = ?
    LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
    setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
    int setmealCount = setmealService.count(setmealLambdaQueryWrapper);


    if(setmealCount > 0) {
      // 已经关联套餐 抛出一个业务异常
    }


    // 如果上面都没有关联 则正常删除
    // 通过super调用父类的mybatis-plus中定义的方法
    super.removeById(id);

  }
}

```

<br>

### 抛出业务异常
一般我们都会在项目中定义自己的业务异常 我们在common包下定义

**目的:**  
定义自己的异常信息

```java
package com.sam.reggie.common;

// 自定义业务异常类
public class CustomException extends RuntimeException {

  // 定义构造器: 传入异常信息
  public CustomException(String message) {
    super(message);
  }
}
```

<br>

**使用方式:**  
这样我们就能定义自己的异常信息了
```java
throw new CustomException("当前分类下关联了菜品, 不能删除");
```

<br>

我们的异常信息需要传递给前端 让前端来显示 要做到这点必须 我们这里既然抛出了异常 则我们需要在 **全局异常处理器** 中统一捕获我们的自定义异常

<Br>

**全局异常处理器**  
也就是service层 抛出的异常 交给全局异常处理器来响应回前端页面

全局异常处理器中可以使用 @ResponseBody 注解, 可以使用return将数据响应回前端

```java
package com.sam.reggie.common;

import java.sql.SQLIntegrityConstraintViolationException;

// 全局异常处理器, 使用annotations属性 拦截Controller层中使用了@RestController注解的类
@ControllerAdvice(annotations = {RestController.class, Controller.class})
// 使用该注解, 最终将JSON数据进行返回
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

  // 处理 sql的异常
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
    log.error("sql侧异常信息为: ", ex.getMessage());
    if(ex.getMessage().contains("Duplicate entry")) {
      String[] strings = ex.getMessage().split(" ");
      String msg = strings[2] + "已存在";
      return Result.error(msg);
    }

    return Result.error("未知错误");
  }




  // 全局异常处理中处理自己的定义的业务异常
  @ExceptionHandler(CustomException.class)
  // 处理方法中要声明该异常类型的形参
  public Result<String> customExceptionHandler(CustomException ex) {
    return Result.error(ex.getMessage());
  }
}

```

<br>

### 完成上面的代码:
```java
package com.sam.reggie.service.impl;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

  @Autowired
  private DishService dishService;

  @Autowired
  private SetmealService setmealService;


  // 根据id删除分类 删除之前 需要进行判断
  @Override
  public void remove(Long id) {

    // 查询当前分类是否关联了菜品 如果已经关联 抛出一个业务异常
    LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
    dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
    int dishCount = dishService.count(dishLambdaQueryWrapper);

    if(dishCount > 0) {
      // 已经关联菜品 抛出一个业务异常
      throw new CustomException("当前分类下关联了菜品, 不能删除");
    }


    // 查询当前分类是否关联了套餐 如果已经关联 抛出一个业务异常
    LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
    setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
    int setmealCount = setmealService.count(setmealLambdaQueryWrapper);

    if(setmealCount > 0) {
      // 已经关联套餐 抛出一个业务异常
      throw new CustomException("当前分类下关联了套餐, 不能删除");
    }

    // 如果上面都没有关联 则正常删除
    // 调用父类的mybatis-plus中定义的方法
    super.removeById(id);

  }
}

```

<br>

**controller层:**  
```java
// 根据id删除分类
@DeleteMapping
// url携带的id 所以我们直接定义形参就可以, 前端判断需要的是code 所以返回值中我们传入 String 就可以
public Result<String> deleteCategory(Long id) {

  // 调用我们service层中自定义的方法 内部会处理判断逻辑
  categoryService.remove(id);

  return Result.success("删除分类成功");
}
```

<br><br>

# 修改分类:

## 需求分析
在分类管理列表页面会有 [修改] 按钮, 点击该按钮的时候 在修改窗口回显分类信息并进行修改, 最后点击确定按钮发送请求 完成修改操作

<br>

这个部分的回显操作 不是和后台交互获取数据的 而是前端在点击[修改]按钮的时候 会携带该行的参数, 通过这种方式进行回显的
```js
@click="editHandler(scope.row)"
```

<br>

### 点击按钮发送请求:
- 请求地址: /category
- 请求方式: put
- 请求参数: {id, name, sort}

<br><br>

## 后台控制层
修改方法是 update() 系列

```java
// 修改菜品的处理
@PutMapping
public Result<String> updateCategory(@RequestBody Category category) {
  categoryService.updateById(category);
  return Result.success("修改分类成功");
}
```

<br><br>

# 文件的上传
菜品管理模块中需要上传图片

<br>

### 文件上传的介绍
文件上传 也称为upload 是指将本地图片 视频 音频等文件上传到服务器上

可以供其他用户浏览货下载的过程 文件上传在项目中应用非常广泛 我们经常发微博 发微信朋友圈都用到了文件上传功能

<br>

### 文件上传 对前端表单的要求
一般都是使用UI框架 但是底层确实是基于原生的html来实现的

- method: post
- enctype: multipart/form-data
- type: file

<br>

### 前端相关:
文件上传的表单项触发 onchange 的时候 触发的回调中 主要做了两件事情

1. 获取文件名的后缀 做文件类型判断
2. 获取文件大小 做上传文件的大小判断

然后表单提交的时候 发起真正的请求
```js
onChange (file) {
  if(file){
    const suffix = file.name.split('.')[1]
    const size = file.size / 1024 / 1024 < 2
    if(['png','jpeg','jpg'].indexOf(suffix) < 0){
      this.$message.error('上传图片只支持 png、jpeg、jpg 格式！')
      this.$refs.upload.clearFiles()
      return false
    }
    if(!size){
      this.$message.error('上传文件大小不能超过 2MB!')
      return false
    }
    return file
  }
},
```

<br>

### 后台相关: 

### 控制器方法接受前端上传的文件 声明 MultipartFile类型 形参
服务器接收客户端页面上传的文件, 我们通常会使用Apache的两个组件

- commons-fileupload
- commons-io

<br>

Spring框架在spring-web包中对文件上传进行了封装 大大简化了服务端代码

我们 **只需要在Controller的方法中声明一个 MultipartFile 类型的参数即可接受上传的文件**

```java
public Result<String> upload(MultipartFile file) { ... }
```



## 文件上传代码部分
这里我们使用的就是demo演示 跟项目无关

<br>

### 请求参数:
- 请求地址: /common/upload
- 请求方式: post
- 请求参数: file

<br>

### 文件上传的演示:
这里我们先使用一个Demo来测试下文件上传的功能 跟项目本身无关

<br>

**要点:**  
前端上传图片, 后台肯定要接收前端上传的文件, 后台如何接收呢? 就比如nodejs中我们配置了依赖后 可以使用req.files来接收上传的文件数据

<br>

### SpringBoot接收上传的文件
SpringBoot中我们可以直接在控制器方法中声明参数即可

<br>

**方式1: 声明 (MultipartFile file) 参数**  
1. 参数类型 必须是 MultipartFile
2. 参数名 必须和前端file表单项的name值一致

<br>

**方式2: 使用该注解 <font color="#C2185B">(@RequestPart("文件表单项中的name值一致") MultipartFile 自定义形参名)</font>**  
将接收到的文件数据 交给我们自己定义的形参, 该方式我们可以自定义形参名

<br>

### @RequestPart 注解
用于处理multipart/form-data类型的请求。通常用于上传文件等场景。
@RequestPart注解还支持更广泛的类型，包括JSON和XML。

- @RequestParam注解: 用于从请求参数中获取单个值
- @RequestPart注解: 用于从multipart/form-data类型的请求中获取一个或多个部分。

```java
@PostMapping("/upload")
public void uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("metadata") String metadata) {
    // 处理文件上传逻辑
}
```

<br>

**与@Multipart注解相比**  
@RequestPart注解更加灵活，可以处理更多类型的请求。

@Multipart注解只能处理multipart/form-data类型的请求，而@RequestPart注解可以处理更多类型的请求，包括JSON和XML。

另外，@Multipart注解不支持多部分请求，而@RequestPart注解可以处理多个部分。

<br>

**注意:**  
在使用@RequestPart注解时，如果您指定了一个部分的名称，那么Spring Boot将会尝试从multipart/form-data类型的请求中获取这个指定的部分数据，如果请求中不包含该部分，则会抛出异常。

@RequestPart注解中指定了"file"作为参数名，表示我们要获取请求中名为"file"的文件部分数据。如果请求中不包含名为"file"的文件部分数据，则会抛出异常。

<br>

此外，当使用@RequestPart注解处理文件上传时，必须确保请求中包含文件部分，否则将抛出异常。

个异常通常是MissingServletRequestPartException类型的异常，它会告诉您请求中缺少了指定的部分。因此，在使用@RequestPart注解时，一定要确保请求中包含了指定的部分数据，否则您的代码将无法正常工作。

<br>

### MultipartFile file 接收的文件是临时文件
我们在控制器方法中接收到的file是临时文件 如果我们没有做转存的处理 那么该次请求结束后 临时文件就会从内存中消失

<br>

**<font color="#C2185B">file对象.transferTo()</font>**  
调用file对象身上的方法, 将文件转存到一个指定的位置

**参数:**  
File file, 通过File对象指定我们的转存的位置

<br>

**注意:**  
我们指定的位置 必须要存在(但是可以通过判断目录是否存在 不存在则创建一个目录)

<br>

### 实现功能:

**@Value注解读取application.yml中定义的数据**  
我们可以通过该注解读取 项目配置文件中定义的自定义数据, 比如我们可以将 文件上传后保存路径 定义在配置文件中 供整个项目读取使用

1. 在application.yml中定义数据:
```s
# 自定义属性:
reggie:
  path: /Users/liulin/Desktop/test/
```

2. 使用 @Value("${reggie.path}") 读取数据 并将其放入到注解所标识的变量中
```java
public class CommonController {

  @Value("${reggie.path}")
  private String basePath;

}
```

<br>

**要点1:**  
我们会将文件上传到一个指定的目录下, 这个指定的目录必须要提前创建好 不然会报错 所以我们可以利用如下的逻辑 判断如果该目录不存在 则先创建
```java
public class CommonController {

  @Value("${reggie.path}")
  private String basePath;

  @PostMapping("/upload")
  public Result<String> upload(MultipartFile file) {

    // 创建一个目录对象
    File dir = new File(basePath);
    // 如果该目录不存在则创建该目录, 则创建该目录
    if(!dir.exists()) {
      dir.mkdirs();
    }
  }

}
```

这样能确保该目录结构一定是存在的

<br>

**要点2:**  
获取上面文件的原文件名
```java
String filename = file.getOriginalFilename();
```

<br>

**要点3:**  
为了防止上传文件名重复会覆盖的情况 我们使用uuid来自定义文件名
```java
// 获取原文件名
String originalFilename = file.getOriginalFilename();

// 获取文件名后缀
String suffix = originalFilename.substring(originalFilename.lastIndexOf("."))

// 获取新文件名: uuid要toString
String filename = UUID.randomUUID().toString() + suffix
```

<br>

**要点4:**  
上传文件成功后 一般会将上传后的新的文件名返回给前端 供前端来使用

<br>

### 代码部分:
```java
@PostMapping("/upload")
// 文件上传表单项的name名为file 所以我们的形参名为file
public Result<String> upload(MultipartFile file) {

  // 创建一个目录对象
  File dir = new File(basePath);
  // 如果该目录不存在则创建该目录
  if(!dir.exists()) {
    // 创建该目录
    dir.mkdirs();
  }
  
  // 获取原始的文件名(获取上传文件的文件名)
  String originalFilename = file.getOriginalFilename();

  // 获取文件名后缀
  String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

  // 获取新的文件名
  String fileName = UUID.randomUUID().toString() + suffix

    // 将临时文件转存到指定的位置
  try {
    file.transferTo(new File(basePath + fileName));
  } catch (IOException e) {
    e.printStackTrace();
  }

  // 文件上传的返回值最好的 filename 因为前端需要上传后的文件名, 因为dish表中的image字段存储的就是上传后的文件名
  return Result.success(fileName);
}
```

<br><br>

# 文件的下载

## 文件下载介绍
文件下载 也称为download 是指将文件从服务器传输到本地计算机的过程

<br>

**通过浏览器进行文件下载, 通常有两种表现形式:**
1. 以附件形式下载 弹出保存对话框, 将文件保存到指定的磁盘目录
2. 直接在浏览器中打开

通过浏览器进行文件下载 本质就是服务端将文件以流的形式写回浏览器的过程

<br><br>

## 梳理流程
文件下载是需要客户端先发起请求, 然后后台通过输出流的方式将文件数据写回到浏览器

比如前端可以通过 ``<img>``标签来发起请求
```html
<img v-if="imgUrl" :src="imgUrl" />
```

<br>

**向/common/download接口发起请求**  
```js
// 上传完成后的回调
handleAvatarSuccess(res, file, fileList) {
  this.imgUrl = `/common/download?name=${res.data}`
}
```

<br>

### 实现逻辑
1. 文件下载要通过输入流 和 输出流配置完成
  - 输入流: 将文件读取到内存中
  - 输出流: 将文件写回浏览器

2. 获取输出流的方式: res.getOutputStream();

```java
// 文件下载:
// 通过输出流向浏览器页面写数据就可以了 不需要返回值
@GetMapping("/download")
// name: 接收url上的name参数 要下载的文件名
public void download(String name, HttpServletResponse res) {


  // 通过输入流: 根据文件名 将文件读取到内存中
  try {
    FileInputStream fis = new FileInputStream(new File(basePath + name));

    // 通过输出流: 将文件写回浏览器 在浏览器展示图片
    ServletOutputStream outputStream = res.getOutputStream();


    // 设置响应回去的文件类型
    res.setContentType("image/jpeg");

    byte[] bytes = new byte[1024];
    int len = 0;
    while((len = fis.read(bytes)) != -1) {
      outputStream.write(bytes, 0, len);
      outputStream.flush();
    }


    // 关闭资源
    outputStream.close();
    fis.close();

  } catch (Exception e) {
    e.printStackTrace();
  }
}
```

<br><br>

# 新增菜品

## 需求分析
后台系统中可以管理菜品信息, 通过新增功能来添加一个新的菜品 在添加菜品时需要选择当前菜品所属的菜品分类

并且需要上传菜品的图片, 在移动端会按照菜品分类来展示对应的菜品信息

<br><br>

## 数据模型
新增菜品 其实就是将新增页面录入的菜品信息插入到dish表, 如果添加了口味做法 还需要向dish_flavor表插入数据

所以在新增菜品时, 涉及到两个表

- dish: 菜品表
- dish_flavor: 菜品口味表

<br>

### 菜品口味实体类
```java
package com.sam.reggie.entity;


/**
 菜品口味
 */
@Data
public class DishFlavor implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;


  //菜品id
  private Long dishId;


  //口味名称
  private String name;


  //口味数据list
  private String value;


  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;


  @TableField(fill = FieldFill.INSERT)
  private Long createUser;


  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;


  //是否删除
  private Integer isDeleted;

}
```

<br><br>

## 新增菜品: 梳理逻辑
这里我们梳理下新增菜品时 前端页面和服务端的交互过程, 也就是下面的4次请求

1. 点击 [新建菜品] 按钮后, 会进入到新建菜品的form页面(backend/page/food/add.html), 页面加载时会发送ajax请求, 请求服务器 菜品分类 数据, 并将展示到 [菜品分类] 下拉框中

2. 上传 [菜品图片] 时, 会发送请求进行图片上传, 请求服务器将图片保存到服务器

3. [菜品图片] 回显时, 会通过 ``<img src>`` src地址自发请求到服务器, 请求图片路径进行回显

4. 点击 [保存] 按钮 发送ajax请求 将菜品相关数据以json形式提交到服务器

<br>

开发新增菜品功能 其实就是在服务器端编写代码去处理前端页面发送的这4次请求

<br>

**注意:**  
图片的上传 和 回显上面已经完成了 就是请求的 /common/upload

这里需要注意 文件上传 和 下载的目录指定是在 application.yml 配置文件中指定了, 尤其是图片的回显会自动向该目录请求图片资源

我们将该目录定义为桌面了, 

<br><br>

## 新增菜品: 请求菜品分类数据
点击 [新建菜品] 按钮后, 会进入到新建菜品的form页面(backend/page/food/add.html), 页面加载时会发送ajax请求, 请求服务器 菜品分类 数据, 并将展示到 [菜品分类] 下拉框中

<br>

### 前端页面相关
新建菜品页面在一加载的时候就会发起请求
```js
getDishList () {
  // api方法：
  getCategoryList({ 'type': 1 }).then(res => {
    if (res.code === 1) {
      this.dishList = res.data
    } else {
      this.$message.error(res.msg || '操作失败')
    }
  })
},
```

<br>

- 请求地址: /category/list
- 请求方法: get
- 请求参数: type
- 响应数据:
  - code
  - data

<br>

### 控制器方法:
根据条件动态的查询分类的数据

1. 控制方法的返回值是 List集合

2. 控制器方法中接收前端的请求参数 最好使用实体类, 因为后期如果扩展请求参数 它更加的好一些

3. 在查询数据库的时候最好判断下参数是否为空

4. 下面的查询中填入了排序, 因为我们的分类数据表中有sort字段, 优先级高的排在上面


```java
@GetMapping("/list")
public Result<List<Category>> categoryList(Category category) {

  LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();

  // 判断下 type是否为空, 并添加2级排序
  categoryLambdaQueryWrapper
      .eq(category.getType() != null,  Category::getType, category.getType())
      .orderByAsc(Category::getSort)
      .orderByDesc(Category::getUpdateTime);

  // 查询分类数据集合
  List<Category> list = categoryService.list(categoryLambdaQueryWrapper);

  return Result.success(list);

}
```

<br><br>

## 新增菜品: 保存新增的菜品
当用户添加完新增菜品的数据 然后点击 [保存] 时会发请求到服务器

<br>

### 前端相关:
- 请求地址: /dish
- 请求方法: post
- 请求参数: 
```js
{
  "name":"测试菜品001",

  // 最终存到数据库是一 分 为单位, 前端自动加了两个0
  "price":10000,

  "code":"",

  "image":"441cb81e-f116-4e01-af6b-6586e40c94f5.jpg",

  "description":"hello",

  // 1起售, 0停售, 新增时肯定是起售状态
  "status":1,

  // 菜品分类id
  "categoryId":"1397844263642378242",

  // 口味
  "flavors":[{"name":"甜味","value":"[\"少糖\"]",
  "showOption":false}]
  }
```

<br><br>

## 后台相关:

### 要点1:
1. 我们保存 菜品数据 保存到 dish 数据表
  - name
  - categoryId
  - price
  - code
  - image
  - description
  - status
  - sort

2. 我们保存 口味数据 保存到 dish_flavor 数据表
  - dishId
  - name
  - value

也就是说我们在插入这两张表的时候 需要整理各个表所需要的数据

<br>

**dish数据表字段:**  
```java
private Long id;

//菜品名称
private String name;

//菜品分类id
private Long categoryId;

//菜品价格
private BigDecimal price;

//商品码
private String code;

//图片
private String image;

//描述信息
private String description;

//0 停售 1 起售
private Integer status;

//顺序
private Integer sort;

private LocalDateTime createTime;

private LocalDateTime updateTime;

private Long createUser;

private Long updateUser;

//是否删除
private Integer isDeleted;
```

<br>

**dish_flavor数据表字段:**  
```java
private Long id;

//菜品id
private Long dishId;

//口味名称
private String name;

//口味数据list
private String value;

private LocalDateTime createTime;

private LocalDateTime updateTime;

private Long createUser;

private Long updateUser;

//是否删除
private Integer isDeleted;
```

<br>

### 要点2:
本次请求提交的数据 我们使用什么来接收 Dish实体类是不行的 因为我们提交的数据中 有一个flavor属性, 该属性并不在Dish实体类中

<br>

**解决方式:**  
既然大部分的请求参数在Dish实体类中, 而有的参数不在Dish实体类中 当有这种情况发生的时候 

**我们新创建一个 DTO实体类** 用于接收前端这种情况的参数

<br>

**DTO使用场景:**  
之前我们没有使用过DTO, 因为前面的情况 前端发送的参数都是和实体类一一对应的

当前端传送的数据 和 实体类中的属性 不是一一对应的时候, 我们就需要使用专门的DTO来传输

<br>

**DTO:**  
全称data transfer object, **即数据传输对象**, 一般用于展示层页面与服务层之间的数据传输

<br>

**DishDto:**  
dto都会有自己的包

DishDto会继承Dish, 也就是说Dish中的属性 它都有 同时扩展新的属性

```java
package com.itheima.reggie.dto;

@Data
public class DishDto extends Dish {

  // 在Dish的基础上 扩展flavors
  private List<DishFlavor> flavors = new ArrayList<>();

  private String categoryName;

  private Integer copies;
}

```

<br>

**flavors:**  
前端页面传递口味的时候是一个数组, 其中每一个数组元素都是一个对象, 每一个对象就表示一个DishFlavor的实例对象

所以在声明的时候将其声明为 ``List<DishFlavor>``
```js
"flavors":[{"name":"甜味","value":"[\"少糖\"]","showOption":false}]
```

<br>

**categoryName:**  
暂时没有用到, 后续会使用

<br>

**copies:**  
暂时没有用到, 后续会使用

<br>

### 要点3:
我们要在该次请求中向两张表添加数据 所以我们要在DishService接口中扩展我们自己的方法 来处理两张表的逻辑

<br>

**DishService接口:**  
```java
package com.sam.reggie.service;

public interface DishService extends IService<Dish> {

  // 名字起的具体些
  void saveWithFlavor(DishDto dishDto);
}
```

<br>

### 要点4:
实体类中多出来的属性 不会被保存到数据库  

因为DishDto实体类 继承了 Dish实体类, 意味着Dish实体类中的属性 DishDto 它都有

所以在我们向数据库保存Dish的数据的时候 可以直接使用DishDto

<br>

### 要点5:
因为我们操作了两张表 所以要在方法上加入事务功能
1. 业务层实现类中的方法上加入 @Transactional 注解
2. 主启动类要开启事务功能支持 @EnableTransactionManagement

<br>

**DishServiceImpl实现类:**  
新增菜品 同时保存对应的口味数据

```java
package com.sam.reggie.service.impl;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

  @Autowired
  private DishFlavorService dishFlavorService;

  // 扩展自己的service方法 用于处理前端新增菜品时 需要往两张表中插入数据的问题
  @Override
  @Transactional
  public void saveWithFlavor(DishDto dishDto) {
    

    // 保存菜品的基本信息到菜品表 dish
    /*
      不用这么写
      Dish dish = new Dish();
      dish.setName(dishDto.getName());
      dish.setCategoryId(dishDto.getCategoryId());
      dish.setPrice(dishDto.getPrice());
      dish.setImage(dishDto.getImage());
      dish.setDescription(dishDto.getDescription());
      dish.setStatus(dishDto.getStatus());
    */
    
    // 因为实体类中多出来的属性不会被保存到数据库, 所以DishDto实体类 可以当做是Dish来用(因为继承)
    this.save(dish);



    // 保存菜品口味数据到菜品表 dish_flavor
    /*
    
      我们使用 MyBatis 的批量保存的方式
      List<DishFlavor> flavors = dishDto.getFlavors();

      for (DishFlavor flavor : flavors) {
        DishFlavor dishFlavor = new DishFlavor();
        dishFlavor.setName(flavor.getName());
        dishFlavor.setValue(flavor.getValue());
        dishFlavorService.save(dishFlavor);
      }
    */
    


    /*
      保存菜品口味数据到菜品表 dish_flavor
      dish_flavor表中有3个字段是必须的
      - dishId
      - name
      - value

      而我们前台传递的数据中只有 flavors数组 并没有 dishId 只有name和value, 所以我们要处理下 dishId的问题

      上面我们已经将新增菜品保存到数据库了, 所以我们可以拿到插入数据库后的主键

      因为 mybatis-plus 中的 insert方法不仅可以完成插入数据的操作, 还可以在插入数据后 通过实体类获取新插入数据的主键
    */
    // 通过实体类本身拿到插入数据库之后的主键
    Long dishId = dishDto.getId();

    List<DishFlavor> flavors = dishDto.getFlavors();
    for (DishFlavor flavor : flavors) {
      flavor.setDishId(dishId);
    }

    dishFlavorService.saveBatch(flavors);
  }
}
```

<br>

**控制层方法:**  
```java
@PostMapping
// 使用DishDto来接收前端的请求参数
public Result<String> save(@RequestBody DishDto dishDto) {
  // 往两张表中插入数据的逻辑 丢到service层中处理
  dishService.saveWithFlavor(dishDto);
  return Result.success("添加菜品成功");
}
```

<br><br>

# 菜品信息的分页查询

## 需求分析
系统中的菜品列表需要分页处理, 我们需要在列表上展示菜品数据表中的基本信息之外 还要展示菜品所对应的图片 和 菜品所对应的分类名称

<br>

### 要点:
1. 我们需要在菜品列表中展示 图片
2. 我们需要在菜品列表中展示 菜品分类

<br>

**图片问题:**  
页面会利用 ``<img src>`` 自发请求, 服务器将图片的二进制数据响应回页面 页面就会自动展示
 
<br>

**展示 菜品分类:**  
在 Dish菜品表 中 并没有Category的分类名称, 我们的菜品表中只保存了category_id

我们要展示分类的名称就需要查询分类表

<br><br>

## 梳理过程
1. 列表页面在加载的时候就会发起ajax请求 并携带 page pageSize name等参数 获取分页数据
  - 请求地址: /dish/page
  - 请求方式: get
  - 请求参数: page pageSize name

2. 列表页面还会发起图片下载的请求, 也就是列表中图片的src属性的自发请求

<br><br>

## 控制器方法:
```java
@GetMapping("/page")
public Result<Page<Dish>> list(Integer page, Integer pageSize, String name) {


  // 创建Page对象 传入分页数据
  Page<Dish> dishPage = new Page<>(page, pageSize);


  // 创建条件构造器 判断name是否为空 为空则不拼接 使用模糊查询
  LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
  dishLambdaQueryWrapper
      .like(StringUtils.isNotBlank(name), Dish::getName, name)
      .orderByDesc(Dish::getUpdateTime);


  // 调用分页方法
  dishService.page(dishPage, dishLambdaQueryWrapper);


  // 我们返回的是 Page<Dish>
  return Result.success(dishPage);
}
```

<br>

### 问题:
我们上面的代码有一个问题, 就是菜品分类列表中有 [菜品分类] 这一列是空白 没有任何数据

这是因为我们返回的Page对象中包含的是Dish实体类, ``Page<Dish>``

而Dish实体类中并没有categoryName属性(dish数据表中没有category_name字段)

Dish实体类中只有categoryId属性, 也就是说我们缺少一个categorName属性

<br>

**也就是说我们返回的 ``Page<Dish>`` 中的属性不够, 我们怎么才能让其多返回一个字段呢?**

因为页面需要什么样的数据 服务端就要对应返回响应的数据, 现在页面不仅仅需要Dish中的数据, 还额外需要categoryName

<br>



<br>

### 解决方法:
我们还是需要使用 DishDto, **DishDto继承了Dish** 所以DishDto中不仅有Dish中所有的属性 还可以扩展自己的 categoryName属性

这样我们查询出来的新的字段就有存放的实体类了

<br>

**思路:**  
我们只有 Dish -> DishService -> DishMapper 也就是说我们能通过DishService查询到的只有 dish 表

**首先**, 所以我们先通过DishService查询分页数据, **这样查询到的数据就会在 dishPage 对象中**
```java
// dishPage 对象
Page<Dish> dishPage = new Page<>(page, pageSize);

// 条件
LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
dishLambdaQueryWrapper
  .like(StringUtils.isNotBlank(name), Dish::getName, name)
  .orderByDesc(Dish::getUpdateTime);

// 查询
dishService.page(dishPage, dishLambdaQueryWrapper);
```

<br>

**然后**, 我们创建 DishDto Page对象
```java
Page<DishDto> dishDtoPage = new Page<>();
```
这时dishDtoPage对象中是空的, 但是它包括了页面中需要用到的categoryName属性

接下来我们就要为这个空的dishDtoPage对象进行赋值

<br>

**page对象中的属性:**  
```java
page: {
  List<T> records
  long size
  long current
  <OrderItem> orders
  boolean optimizeCountSql
  boolean isSearchCont
  boolean hitCount
  String countId
  Long maxLimit
}
```

<br>

**首先**, 我们为 dishDtoPage 中除了 records属性 之外的属性进行赋值

<br>

**<font color="#C2185B">BeanUtils.copyProperties(Object source, Object target, [String ignoreProperties])</font>**  
将source中的属性复制到target中, 如果有不想复制的属性就使用参数3来指定

```java
BeanUtils.copyProperties(dishPage, dishDtoPage, "records");
```

<br>

**然后**, 我们为 dishDtoPage 中 records 属性进行赋值

1. 从dishPage对象中获取records(它里面有除了categoryName之外的数据了)

2. 遍历该集合, 完成如下的逻辑
  1. 创建 dishDto 对象
  2. 使用 BeanUtils.copyProperties 方法将 ``List<Dish>`` 中的每一个Dish对象中过的数据 赋值给 ``List<DishDto>`` 中的每一个DishDto对象

  3. 额外处理DishDto中的categoryName属性
    1. 拿到Dish中的categoryId
    2. 根据categoryId查询数据库获取到category对象
    3. 拿到category对象中的categoryName为DishDto中的categoryName属性赋值

4. 将处理好后的``List<DishDto>`` 集合赋值给 dishDtoPage对象中的 records 属性

5. 控制器方法内最终返回dishDtoPage

<br>

**总结:**  
当返回的数据 没有办法使用一个实体类承装的时候 我们可以使用该实体类的Dto, 通过继承的方式扩展额外的属性

为DishDto实体类赋值的时候 还是可以通过Dish类使用 ``BeanUtils.copyProperties(dishPage, dishDtoPage)`` 的方式赋值

<br>

### 代码部分:
```java
@GetMapping("/page")
public Result<Page> list(Integer page, Integer pageSize, String name) {

  // 通过 dishService 查询 dish 表, 将查询结果封装在 dishPage 对象中
  Page<Dish> dishPage = new Page<>(page, pageSize);
  
  // 创建条件构造器 判断name是否为空 为空则不拼接 使用模糊查询
  LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
  dishLambdaQueryWrapper
      .like(StringUtils.isNotBlank(name), Dish::getName, name)
      .orderByDesc(Dish::getUpdateTime);
  
  // 调用分页方法 将查询到的dish表中的数据 存放到dishPage中
  dishService.page(dishPage, dishLambdaQueryWrapper);
  
  
  
  // Dish对象中的属性不够, 页面中还需要使用 categoryName 属性, 我们使用Dto模式来扩展Dish实体类 扩展categoryName
  Page<DishDto> dishDtoPage = new Page<>();   // 空的page对象 我们要为它进行赋值操作
  
  // 为dishDtoPage的属性进行赋值(除了records属性) 
  BeanUtils.copyProperties(dishPage, dishDtoPage, "records");

  // 为dishDtoPage的 records 属性进行赋值
  // 1. 从 dishPage 中获取它的 records 集合(里面是有数据的)
  List<Dish> dishRecords = dishPage.getRecords();

  // 遍历dishRecords集合 拿到每一个Dish对象(item)
  List<DishDto> list = dishRecords.stream().map(item -> {

    // 创建 DishDto 对象
    DishDto dishDto = new DishDto();

    // 给dishDto中除了categoryName属性之外的属性赋值
    BeanUtils.copyProperties(item, dishDto);

    // 获取每一个菜品的分类id
    Long categoryId = item.getCategoryId();

    // 根据分类id查询category表 获取分类对象 从而拿到categoryName
    Category category = categoryService.getById(categoryId);

    // 获取分类名称
    String categoryName = category.getName();

    // 将categoryName赋值给dishDto
    dishDto.setCategoryName(categoryName);

    // 返回dishDto
    return dishDto;
    
    // 获取到有数据的 List<DishDto>
  }).collect(Collectors.toList());


  // 将处理后的 records 集合 set到 dishDtoPage 对象中
  dishDtoPage.setRecords(list);

  
  // 最后我们返回加工后的dto对象
  return Result.success(dishDtoPage);
}
```

<br><br>

# 修改菜品: 页面回显

## 需求分析:
在菜品管理页面的列表中 我们可以点击 [修改] 按钮 会跳转到修改菜品页面 用户可以编辑信息 点击 [保存] 按钮来完成操作

在 修改菜品 页面 需要回显菜品信息

<br><br>

## 梳理逻辑:
在菜品管理页面的列表中 我们可以点击 [修改] 按钮, 会携带当前行数据id 跳转到 修改菜品页面(add.html)

页面在加载的时候 会先后发起两次请求
1. 发送get请求 请求 /category/list 接口 获取菜品分类数据 用于菜品分类下拉框中数据展示 **新增菜品时 已完成**

2. 获取url上的参数 id 调用 init() 方法, 请求该行菜品数据用于回显数据
  - 请求地址: `/dish/${id}`,
  - 请求方式: get
  - 注意该次请求中还要包含 flavors 属性(查询dish_flavor表)

3. 页面发送请求 请求服务端进行图片下载 用于页面图片回显 **文件下载时 已完成**

4. 点击 [保存] 按钮, 页面发送ajax请求将修改后的菜品相关数据以json形式提交服务端

<br><br>

## 功能实现

### 要点:
修改菜品页面要回显数据 要回显的是两张表的数据, 所以Controller中的返回值需要是 DishDto
- dish表中的数据
- dish_flavor表中的数据

<br>

**DishService:**  
```java
public interface DishService extends IService<Dish> {
  // 新增菜品 同时插入菜品对应的口味数据 需要操作两张表 dish dish_flavor
  void saveWithFlavor(DishDto dishDto);

  // 根据id查询菜品信息 和 对应的口味信息
  DishDto getDishById(Long id);
}
```

<br>

**DishServiceImpl:**  
```java
// 根据id查询菜品信息 和 对应的口味信息
@Override
public DishDto getDishById(Long id) {
  // 查询 菜品信息
  Dish dish = this.getById(id);


  // 查询 口味信息, 根据dishId获取口味
  Long dishId = dish.getId();

  LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
  dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
  List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);

  // 创建DishDto 并为它的各个属性进行赋值
  DishDto dishDto = new DishDto();
  BeanUtils.copyProperties(dish, dishDto);

  // 为DishDto中的flavors属性进行赋值
  dishDto.setFlavors(list);

  return dishDto;
}
```

<br>

**Controller:**  
注意id的类型是Long
```java
// 根据id查询 菜品信息 和 对应的口味信息 需要的数据进行回显 要查询两张表
@GetMapping("/{id}")
public Result<DishDto> getDishById(@PathVariable Long id) {
  DishDto dishDto = dishService.getDishById(id);
  return Result.success(dishDto);
}
```

<br><br>

## 修改菜品: 保存修改数据
当用户在 修改菜品 页面点击 [保存] 按钮后可以保存数据

- 请求地址: /dish
- 请求方式: put
- 请求参数: 页面form表单项

<br>

### 要点1:
我们前端提交的修改数据 分别存储在两张表中
- dish
- dish_flavor

<br>

所以我们在保存的时候 也要将数据保存在两张表中

<br>

### 要点2:
我们要使用 DishDto 来承装前端发送过来的数据

<br>

### 要点3: 
我们要在 Service层中处理分别往两张表中添加数据的逻辑

<br>

### 要点4:
我们要使用事务的功能, 因为我们插入数据了

<br>

### 要点5:
DishDto中的flavors属性 仅仅是dish_flavor表中的 name 和 value字段 我们还需要额外的处理 dish_id 字段

<br>

**DishServiceImpl:**
```java
// 保存修改菜品信息
  @Override
  @Transactional
  public void updateDishAndDishFlavor(DishDto dishDto) {


    // 更新菜品表的信息, dishDto是Dish的子类 所以没有问题
    this.updateById(dishDto);


    // 修改口味数据的方式
    // 1. 先清理菜品对应的口味数据 -- delete操作 根据dishId
    LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
    dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
    // 这里不能使用 removeById 它需要dishFilavor的id, 这里是根据dishId删除
    dishFlavorService.remove(dishFlavorLambdaQueryWrapper);


    // 2. 再添加新的菜品对应的口味 -- insert操作
    // 将数据保存到 dish_flavor 表, 并额外处理 dishId
    List<DishFlavor> flavors = dishDto.getFlavors();
    flavors = flavors.stream().map(item -> {
      item.setDishId(dishDto.getId());
      return item;
    }).collect(Collectors.toList());
    dishFlavorService.saveBatch(flavors);
  }
```

<br>

**Controller:**  
```java
// 保存修改菜品的数据
@PutMapping
public Result<String> save(@RequestBody DishDto dishDto) {
  dishService.saveDishAndDishFlavor(dishDto);
  return Result.success("修改菜品信息成功");
}
```

<br>

**测试:**  
```sql
-- 测试: 1639675682198351873
select d.id, d.name, d.category_id, df.id, df.dish_id, df.name, df.value 
from dish d
	inner join dish_flavor df
	on d.id = df.dish_id
where d.id = 1639675682198351873
```

<br><br>

# 套餐管理
套餐是一组菜品的组合

后台系统中可以管理套餐信息 通过新增套餐功能来添加一个新的套餐

在添加套餐的时候需要选择当前套餐所属的套餐分类 和 包含的菜品, 并且需要上传套餐对应的图片 

在移动端会按照套餐分类来展示对应的套餐

<br><br>

## 数据模型
新增套餐 就是将新增页面录入的信息插入到 setmeal 表中

还需要向 setmeal_dish 表, 插入套餐和菜品关联的数据 所以新增套餐的时候 涉及到两张表:
- setmeal: 套餐表
- setmeal_dish: 套餐菜品关系表

<br>

**setmeal套餐表字段:**  
```sql
id category_id name price status code desc img
```

<br>

**setmeal_dish套餐菜品关系表字段:**
```sql
id setmeal_id dish_id name price copies sort
```

<br><br>

## 准备工作
- 创建 SetmealDish 实体类
- 创建 SetmealDto 
- 创建 SetmealDishMapper
- 创建 SetmealDishService
- 创建 SetmealDishServiceImpl
- 创建 SetmealController

<br>

**SetmealDish实体类:**  
name 和 price 都属于冗余字段, 因为我们根据dishId查询都可以能查询到的, 类似这样的情况都属于荣誉字段

这里为了方法都定义在表中

```java
package com.sam.reggie.entity;
/**
 * 套餐菜品关系
 */
@Data
public class SetmealDish implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  //套餐id
  private Long setmealId;

  //菜品id
  private Long dishId;

  //菜品名称 （冗余字段）
  private String name;

  //菜品原价
  private BigDecimal price;

  //份数
  private Integer copies;

  //排序
  private Integer sort;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @TableField(fill = FieldFill.INSERT)
  private Long createUser;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateUser;

  //是否删除
  private Integer isDeleted;
}
```

<br>

**SetmealDto:**  
Dto是子类 它会继承某个类 为某个类来扩展属性 用于前后端交互
```java
package com.sam.reggie.dto;

@Data
public class SetmealDto extends Setmeal {

  private List<SetmealDish> setmealDishes;

  private String categoryName;
}
```

<br><br>

## 梳理交互过程
新增套餐时 前端和后端会进行如下的 请求交互

**请求1: (已实现)**  
套餐管理页面中 点击 [新建套餐] 按钮 会跳转到添加套餐页面(backend/page/combo/add.html)

在页面加载的时候页面会发起请求 响应数据会作为 **套餐分类下拉框中展示**
- 请求地址: /category/list
- 请求方法: get
- 请求参数: 
  - url携带 ?type=2 (套餐的分类)

<br>

**请求2:**  
套餐管理页面中 点击 [新建套餐] 按钮 会跳转到添加套餐页面(backend/page/combo/add.html)

在页面加载的时候页面会发起请求 响应数据会作为 点击 [添加菜品] 后 **弹出的对话框的左侧 菜品列表 使用**
- 请求地址: /category/list
- 请求方法: get
- 请求参数: 
  - url携带 ?type=1 (菜品的分类)

<br>

**该次请求在页面挂着的时候也会发起**, 这样 点击 [添加菜品] 按钮后 第一个选项对应的菜品就会有数据

<br>

**请求3:**  
添加套餐页面中 点击 [添加菜品] 按钮后, 会弹出对话框, 对话框中左侧边栏 有菜品分类按钮, 湘菜 川菜 粤菜 等

点击这些选项会发送对应的请求, 根据菜品的分类查询菜品数据 并展示到窗口中

- 请求地址: /dish/list
- 请求方法: get
- 请求参数: url携带 ?categoryId=1397844263642378242

<br>

**该次请求在页面挂着的时候也会发起**, 这样 点击 [添加菜品] 按钮后 第一个选项对应的菜品就会有数据

<br>

**请求4:**  
页面发送请求进行图片上传 请求服务器将图片保存到服务器

<br>

**请求5:**  
页面发送请求进行图片下载 将上传的图片进行回显

<br>

**请求6:**  
点击 [保存] 按钮 发送ajax请求 将套餐相关数据以json形式提交到服务器 保存到
- 套餐表
- 套餐菜品关系表

<br><br>

## 新增套餐: 根据分类查询菜品 (请求3)
添加套餐页面中 点击 [添加菜品] 按钮后, 会弹出对话框, 对话框中左侧边栏 有菜品分类按钮, 湘菜 川菜 粤菜 等

点击这些选项会发送对应的请求, 根据菜品的分类查询菜品数据 并展示到窗口中

- 请求地址: /dish/list
- 请求方法: get
- 请求参数: url携带 ?categoryId=1397844263642378242

<br>

### 要点:
1. 返回给前端多条数据的时候 控制器方法的返回值类型要定义为 List

2. 如果该控制器方法想要对接收前端参数时 使其扩展性更好 则要声明为实体类类型
  - (Dish dish): 扩展性更好
  - (Long id): 该控制器方法只能接收 Long id 参数

<br>

3. 查询数据为List并要展示到页面的话, 我们可以对数据进行排序 比如 sort字段 或者 updateTime 字段

```java
@GetMapping("/list")
public Result<List<Dish>> list(Dish dish) {

  Long categoryId = dish.getCategoryId();

  LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

  // 判断请求参数是否为空 为空则不拼接sql (在这里判断我觉得不好 因为category_id为null 不拼接的话 查询的就是所有数据)
  // 查询状态为1的 停售的不要查出来
  dishLambdaQueryWrapper
      .eq(dish.getCategoryId() != null, Dish::getCategoryId, categoryId)
      .eq(Dish::getStatus, 1)
      .orderByAsc(Dish::getSort)
      .orderByDesc(Dish::getUpdateTime);
  
  // 停售餐票

  List<Dish> list = dishService.list(dishLambdaQueryWrapper);

  return Result.success(list);
}
```

<br><br>

## 新增套餐: 提交新增的套餐数据 (请求6)
当用户输入完套餐信息 需要保存的时候 会发起如下的请求
- 请求地址: /setmeal
- 请求方法: post
- 请求参数: 
```js
{
  "name": "测试套餐001",
  "categoryId": "1413342269393674242",
  "price": 9900,
  "code": "",
  "image": "d1b72915-9112-40a3-b7f7-37283cf15301.jpg",
  "description": "好吃",
  "status": 1,

  "dishList": [],

  "idType": "1413342269393674242",

  "setmealDishes": [
    {
      "copies": 1,
      "dishId": "1397849739276890114",
      "name": "辣子鸡",
      "price": 7800
    },
    {
      "copies": 1,
      "dishId": "1397850392090947585",
      "name": "组庵鱼翅",
      "price": 4800
    },
    {
      "copies": 1,
      "dishId": "1397850851245600769",
      "name": "霸王别姬",
      "price": 12800
    }
  ]
}
```

<br>

### 要点1:
虽然前端发送请求的时候传递过来上面的数据, 但是我们选择使用 SetmealDto 来接收的时候 它只能接收到 Setmeal + SetmealDto 中有的属性 如下的两个属性是接收不到的: 

- idType: 套餐分类, 它和categoryId的作用是一样的
- dishList: 不知道啥

<br>

### 要点2:
因为是要操作两张表 我们扩展自己的service层 并要在service实现类中添加 @Transactional 注解 来管理事务 保证事务的一致性

<br>

### 要点3:
如果前端没有传递的属性 但是数据表中有的话 需要我们手动赋值

<br>

**controller:**
```java
// 处理 保存添加的套餐信息
@PostMapping
// 接收前端请求参数的形参 不能是 Setmeal实体类 因为还有一个setmealDishes属性并不在Setmeal实体类中 我们要使用SetmealDto
public Result<String> save(@RequestBody SetmealDto setmealDto) {

  /*
    我们新增套餐的操作 需要向两张表中插入数据 所以我们扩展自己的service层来处理
    - setmeal
    - setmeal_dish
  */
  setmealService.saveSetmealAndSetmealDish(setmealDto);
  return Result.success("添加套餐成功");
}
```

<br>

**service:**  
```java
// 处理新增套餐 往两张表中插入数据的逻辑
@Override
@Transactional
public void saveSetmealAndSetmealDish(SetmealDto setmealDto) {
  // 向setmeal表插入数据 执行insert操作
  this.save(setmealDto);


  // 向setmeal_dish表中插入数据 执行insert操作
  /*
    前端提交过来的数据 只有
    "copies": 1,
    "dishId": "1397849739276890114",
    "name": "辣子鸡",
    "price": 7800

    还缺少 所以我们要单独处理这两个属性
    setmeal_id
    sort -> 它有默认值
  */
  List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
  for (SetmealDish setmealDish : setmealDishes) {
    setmealDish.setSetmealId(setmealDto.getId());
  }

  setmealDishService.saveBatch(setmealDishes);
}
```

<br><br>

# 套餐管理: 列表页面分页
- 请求地址: /setmeal/page?page=1&pageSize=10
- 请求方式: get

<br>

### 要点1:
列表页面除了展示 setmeal 的数据之外, 还需要展示套餐分类(categoryName), 而 categoryName 需要从category表中获取

也就是说我们控制器方法直接返回setmealPage对象是不够的, 我们需要返回 SetmealDto 对象, 它扩展了categoryName

<br>

```java
@GetMapping("/page")
public Result<Page> page(Integer page, Integer pageSize, String name) {

  // 查询setmeal表 获取分页数据
  Page<Setmeal> setmealPage = new Page<>(page, pageSize);
  LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
  setmealLambdaQueryWrapper
      .like(StringUtils.isNotBlank(name), Setmeal::getName, name)
      .orderByDesc(Setmeal::getUpdateTime);
  setmealService.page(setmealPage, setmealLambdaQueryWrapper);



  // 因为查询到的分页数据中没有 套餐分类(categoryName) 所以利用dto来扩展属性categoryName
  Page<SetmealDto> setmealDtoPage = new Page<>();

  // 将查询到的数据 除了records 复制到新的setmealDtoPage对象中
  BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");

  // 获取 setmealPage 有数据的 records
  List<Setmeal> records = setmealPage.getRecords();

  // 遍历records, 拿到categoryId 查询category表获取categoryName 并将records中的每一项Setmeal转换为SetmealDto, 并给每一个SetmealDto的categoryName进行赋值
  List<SetmealDto> list = records.stream().map(item -> {
    SetmealDto setmealDto = new SetmealDto();
    BeanUtils.copyProperties(item, setmealDto);

    Long categoryId = item.getCategoryId();
    Category category = categoryService.getById(categoryId);
    String categoryName = category.getName();
    
    setmealDto.setCategoryName(categoryName);
    return setmealDto;
  }).collect(Collectors.toList());

  setmealDtoPage.setRecords(list);

  return Result.success(setmealDtoPage);
}
```