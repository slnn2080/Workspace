package com.sam.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sam.reggie.dto.SetmealDto;
import com.sam.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
  void saveSetmealAndSetmealDish(SetmealDto setmealDto);

  // 删除套餐
  void deleteSetmealAndSetmealDish(List<Long> ids);
}
