package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sam.reggie.common.Result;
import com.sam.reggie.entity.Employee;
import com.sam.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

  // 注入service
  @Autowired
  private EmployeeService employeeService;


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


  // 处理退出登录请求
  @PostMapping("/logout")
  public Result<String> logout(HttpServletRequest req) {
    // 1. 清理session中保存的当前登录员工的id
    req.getSession().removeAttribute("employee");

    // 2. 返回 退出成功 的msg
    return Result.success("退出成功");
  }


  // 处理添加员工的请求, 请求路径是 /employee 类上指定了所以我们这里不用写了
  @PostMapping
  // 因为前端在判断用户是否添加成功的时候 使用的是 code, 所以我们回传给前端的Result里面放个String就可以
  public Result<String> save(@RequestBody Employee employee, HttpServletRequest req) {
    log.info("新增员工, 员工信息: {}", employee.toString());

    // password属性: 设置初始密码(md5加密): 前端新增员工页面form里没有输入初始密码的表单项, 我们在这里给员工设置初始密码
    String pwd = DigestUtils.md5DigestAsHex("123456".getBytes());
    employee.setPassword(pwd);

    // status属性: 数据库中有默认值 可忽略

    /*
      修改为 公共字段自动填充:
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
    */

    // 调用service层的方法
    employeeService.save(employee);
    return Result.success("新增员工成功");
  }


  @GetMapping("/{id}")
  public Result<Employee> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeService.getById(id);
    if(employee != null) {
      return Result.success(employee);
    }
    return Result.error("没有查询到对应的员工信息");
  }


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


  // 根据员工id修改员工的信息
  @PutMapping
  // 返回值泛型: 前端页面需要用到code判断 不需要其它的数据
  public Result<String> update(@RequestBody Employee employee, HttpServletRequest req) {

    /*
      // 修改为自动填充功能
      // 要点: 在做更新操作的时候 我们需要为employee对象中的updateTime updateUser 这两个属性进行赋值 更新时间 和 更新人
      employee.setUpdateTime(LocalDateTime.now());

      Long id = (Long) req.getSession().getAttribute("employee");
      employee.setUpdateUser(id);
    */

    Long id = Thread.currentThread().getId();
    log.info("线程id: {}", id);

    // 调用service修改数据库数据
    employeeService.updateById(employee);

    return Result.success("员工信息修改成功");
  }
}
