package com.sam.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sam.reggie.dto.DishDto;
import com.sam.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
  // 新增菜品 同时插入菜品对应的口味数据 需要操作两张表 dish dish_flavor
  void saveWithFlavor(DishDto dishDto);

  // 根据id查询菜品信息 和 对应的口味信息
  DishDto getDishById(Long id);

  // 保存修改菜品信息
  void updateDishAndDishFlavor(DishDto dishDto);
}
