package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sam.reggie.common.BaseContext;
import com.sam.reggie.common.Result;
import com.sam.reggie.entity.ShoppingCart;
import com.sam.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
  @Autowired
  private ShoppingCartService shoppingCartService;

  /*
    控制器方法
      返回值定义为 ShoppingCart (前端页面要使用数据)
      形参定义为 ShoppingCart (用于接收前端页面的请求参数)

    添加购物车逻辑:
      1. 设置用户id: 我们观察下 购物车表 中的必传字段, 发现 userId 字段是必传项 但是前端的请求参数中 并没有该项 所以 userId 需要手动设置, 不然购物车中的数据都不知道谁点的

      2. 如果同一个菜品 两次添加到购物车 我们将该菜品的number属性修改为2
       - 查询下当前添加进来的菜品或套餐 有没有在购物车中
        - 如果已经存在 我们在number的基础上 + 1
        - 如果没有存在 则添加到购物车中 数量默认就是1
  */
  @PostMapping("/add")
  public Result<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {

    // 1. 设置用户id 指定当前是哪个用户的购物车数据(ThreadLocal 或者 session获取都可以)
    Long userId = BaseContext.getCurrentId();
    shoppingCart.setUserId(userId);

    LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();

    // 通用 查询条件: userId
    shoppingCartLambdaQueryWrapper
        .eq(ShoppingCart::getUserId, userId);

    // 查询当前 菜品 或者 套餐 是否在购物车中, 动态拼接 特有 条件
    Long dishId = shoppingCart.getDishId();
    if(dishId != null) {
      // 添加到购物车的是菜品, 查询条件 userId + dishId
      shoppingCartLambdaQueryWrapper
          .eq(ShoppingCart::getDishId, dishId);

    } else {
      // 添加购物车的是套餐, 查询条件 userId + setmealId
      shoppingCartLambdaQueryWrapper
          .eq(ShoppingCart::getDishId, shoppingCart.getSetmealId());
    }

    // 统一查询: select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
    ShoppingCart cart = shoppingCartService.getOne(shoppingCartLambdaQueryWrapper);


    // 如果能查询到 cart 则说明当前的菜品或套餐已经存在于购物车 则number加1
    if(cart != null) {
      cart.setNumber(cart.getNumber() + 1);
      // 更新该菜品或套餐的number
      shoppingCartService.updateById(cart);
    } else {
      // 如果不存在则将 形参封装的shoppingCart对象添加到数据表中, number第一次进库默认为1
      shoppingCart.setNumber(1);
      shoppingCartService.save(shoppingCart);

      // 当我们将shoppingCart保存到数据库后, shoppingCart对象的id就是添加到数据后的值
      // 作用域原因我们进行如下操作后 统一返回 cart 对象, 因为进入else后 cart 肯定是空
      cart = shoppingCart;
    }

    return Result.success(cart);
  }


  @GetMapping("/list")
  public Result<List<ShoppingCart>> list() {
    Long id = BaseContext.getCurrentId();
    LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
    shoppingCartLambdaQueryWrapper
        .eq(ShoppingCart::getUserId, id)
        .orderByAsc(ShoppingCart::getCreateTime);
    List<ShoppingCart> list = shoppingCartService.list(shoppingCartLambdaQueryWrapper);
    return Result.success(list);
  }


  @DeleteMapping("/clean")
  public Result<String> clean() {
    Long userId = BaseContext.getCurrentId();
    LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
    shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
    shoppingCartService.remove(shoppingCartLambdaQueryWrapper);

    return Result.success("清空成功");
  }
}
