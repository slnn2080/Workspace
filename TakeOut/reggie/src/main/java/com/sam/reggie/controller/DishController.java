package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sam.reggie.common.Result;
import com.sam.reggie.dto.DishDto;
import com.sam.reggie.entity.Category;
import com.sam.reggie.entity.Dish;
import com.sam.reggie.entity.DishFlavor;
import com.sam.reggie.service.CategoryService;
import com.sam.reggie.service.DishFlavorService;
import com.sam.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private DishService dishService;

  @Autowired
  private DishFlavorService dishFlavorService;

  @Autowired
  private CategoryService categoryService;

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


  @PostMapping
  // 使用DishDto来接收前端的请求参数
  public Result<String> saveWithFlavor(@RequestBody DishDto dishDto) {
    // 往两张表中插入数据的逻辑 丢到service层中处理
    dishService.saveWithFlavor(dishDto);

    Set keys = redisTemplate.keys("dish_*");
    redisTemplate.delete(keys);

    return Result.success("添加菜品成功");
  }


  // 根据id查询 菜品信息 和 对应的口味信息 需要的数据进行回显 要查询两张表
  @GetMapping("/{id}")
  public Result<DishDto> getDishById(@PathVariable Long id) {
    DishDto dishDto = dishService.getDishById(id);
    return Result.success(dishDto);
  }


  // 保存修改菜品的数据
  @PutMapping
  public Result<String> update(@RequestBody DishDto dishDto) {
    dishService.updateDishAndDishFlavor(dishDto);

    // 清理方案1: 清理所有菜品的缓存数据
    Set keys = redisTemplate.keys("dish_*");
    // redisTemplate.delete()方法 可以传入一个keys集合
    redisTemplate.delete(keys);

    // 清理掉 Redis 中的数据
    /*
      String key = "dish_" + dishDto.getCategoryId() + dishDto.getStatus();
      redisTemplate.delete(key);
    */
    return Result.success("修改菜品信息成功");
  }


  /*
    根据条件查询对应的菜品数据

    控制器方法的参数:
      - Long categoryId: 可以选择 没有问题
      - Dish dish: 这样的选择可以让 list接口 更加的通用
  */
//  @GetMapping("/list")
//  public Result<List<Dish>> list(Dish dish) {
//
//    Long categoryId = dish.getCategoryId();
//
//    LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//    // 判断请求参数是否为空 为空则不拼接sql (在这里判断我觉得不好 因为category_id为null 不拼接的话 查询的就是所有数据)
//    // 停售产品不需要查出来
//    dishLambdaQueryWrapper
//        .eq(dish.getCategoryId() != null, Dish::getCategoryId, categoryId)
//        .ne(Dish::getStatus, 0)
//        .orderByAsc(Dish::getSort)
//        .orderByDesc(Dish::getUpdateTime);
//    List<Dish> list = dishService.list(dishLambdaQueryWrapper);
//
//    return Result.success(list);
//  }

  // 迎合前端页面的请求 进一步进行修改
  @GetMapping("/list")
  public Result<List<DishDto>> list(Dish dish) {

    Long categoryId = dish.getCategoryId();

    // 初始化返回结果
    List<DishDto> dishDtoList = null;

    // 设置Redis中获取菜品的key: dish_12124124_1
    String key = "dish_" + categoryId + "_" + dish.getStatus();

    // 1. 从redis中获取缓存数据, 该控制方法最后返回的数据类型就是 List<DishDto>
    dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);


    if(dishDtoList != null) {
      // 如果存在 则直接返回 无需查询数据库
      return Result.success(dishDtoList);
    }

    // 如果不存在 查询数据库 将查询到的菜品数据缓存到Redis
    LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

    // 判断请求参数是否为空 为空则不拼接sql (在这里判断我觉得不好 因为category_id为null 不拼接的话 查询的就是所有数据)
    // 停售产品不需要查出来
    dishLambdaQueryWrapper
        .eq(dish.getCategoryId() != null, Dish::getCategoryId, categoryId)
        .ne(Dish::getStatus, 0)
        .orderByAsc(Dish::getSort)
        .orderByDesc(Dish::getUpdateTime);
    List<Dish> list = dishService.list(dishLambdaQueryWrapper);


    // 我们将上面查询出来的数据 移植到DishDto中, 并从Dish中获取id, 根据dish_id查询dish_flavor表获取数据
    dishDtoList = list.stream().map(item -> {
      // 创建 DishDto
      DishDto dishDto = new DishDto();

      // 给DishDto赋值
      BeanUtils.copyProperties(item, dishDto);

      Long dishCategoryId = item.getCategoryId();
      Category category = categoryService.getById(dishCategoryId);
      String categoryName = category.getName();
      dishDto.setCategoryName(categoryName);

      Long dishId = item.getId();
      LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
      dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
      List<DishFlavor> flavors = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
      dishDto.setFlavors(flavors);

      // 将 Dish -> DishDto
      return dishDto;

    }).collect(Collectors.toList());


    // 将查询到的菜品数据缓存到Redis, 设置数据的过期时间, 60分钟后也会自动清理掉
    redisTemplate.opsForValue().set(key, dishDtoList, 60L, TimeUnit.MINUTES);


    return Result.success(dishDtoList);
  }
}
