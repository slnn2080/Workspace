package com.sam.reggie.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
  public void deleteSetmealAndSetmealDish(Long ids) {
    // setmeal_id

    // 删除套餐表
    this.removeById(ids);

    // 删除套餐菜品关系表
    setmealDishService.removeById(ids);
  }


}
