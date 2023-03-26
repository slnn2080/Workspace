package com.sam.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sam.reggie.common.CustomException;
import com.sam.reggie.entity.Category;
import com.sam.reggie.entity.Dish;
import com.sam.reggie.entity.Setmeal;
import com.sam.reggie.mapper.CategoryMapper;
import com.sam.reggie.service.CategoryService;
import com.sam.reggie.service.DishService;
import com.sam.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
      throw new CustomException("当前分类下关联了菜品, 不能删除");
    }

    // 查询当前分类是否关联了套餐 如果已经关联 抛出一个业务异常
    // 查询dish表: select count(*) from setmeal where category_id = ?
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
