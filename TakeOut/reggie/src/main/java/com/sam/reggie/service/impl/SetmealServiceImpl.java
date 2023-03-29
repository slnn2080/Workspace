package com.sam.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sam.reggie.common.CustomException;
import com.sam.reggie.common.Result;
import com.sam.reggie.dto.SetmealDto;
import com.sam.reggie.entity.Setmeal;
import com.sam.reggie.entity.SetmealDish;
import com.sam.reggie.mapper.SetmealMapper;
import com.sam.reggie.service.SetmealDishService;
import com.sam.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

  @Autowired
  private SetmealDishService setmealDishService;

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


  // 删除套餐
  @Override
  @Transactional
  public void deleteSetmealAndSetmealDish(List<Long> ids) {
    /*
      只有停售的套餐才可以删除 启售状态中的套餐是不能删除的
      查询套餐的状态 确定是否可以删除

      ids是一个id集合, 既然要根据多个id来查询数据表 则使用 in
      select count(*) from setmeal where id in (1,2,3) and status = 1

      我们查询下 前端传递的id 有没有在启售状态的 如果有则抛出业务异常
    */
    LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
    setmealLambdaQueryWrapper
        .in(Setmeal::getId, ids)
        .eq(Setmeal::getStatus, 1);
    int count = this.count(setmealLambdaQueryWrapper);

    // 如果不能删除 抛出一个业务异常
    if(count > 0) {
      // >0 意味着查询出来数据 就说明前端传递的ids中有启售状态的商品 则不能删除 我们要抛出异常
      throw new CustomException("套餐正在售卖中, 不能删除");
    }

    // 如果可以删除 先删除套餐表中的数据
    this.removeByIds(ids);

    /*
      再删除套餐关系表中的数据
      ids是套餐表中的id, 套餐表中的id在setmeal_dish中作为setmeal_id字段出现

      delete from setmeal_dish where setmeal_id in (1,2,3)
    */
    LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
    setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
    setmealDishService.remove(setmealDishLambdaQueryWrapper);

  }


}
