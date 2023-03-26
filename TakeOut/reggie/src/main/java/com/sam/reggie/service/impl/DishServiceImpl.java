package com.sam.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sam.reggie.dto.DishDto;
import com.sam.reggie.entity.Dish;
import com.sam.reggie.entity.DishFlavor;
import com.sam.reggie.mapper.DishMapper;
import com.sam.reggie.service.DishFlavorService;
import com.sam.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

  @Autowired
  private DishFlavorService dishFlavorService;

  // 扩展自己的service方法 用于处理前端新增菜品时 需要往两张表中插入数据的问题
  @Override
  @Transactional
  public void saveWithFlavor(DishDto dishDto) {

    // 保存菜品的基本信息到菜品表 dish
    this.save(dishDto);

    /*
      保存菜品口味数据到菜品表 dish_flavor
      dish_flavor表中有3个字段是必须的
      - dishId
      - name
      - value

      而我们前台传递的数据中只有 flavors数组 并没有 dishId 所以我们要处理下 dishId
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
}
