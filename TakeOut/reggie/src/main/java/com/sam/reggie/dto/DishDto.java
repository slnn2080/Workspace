package com.sam.reggie.dto;

import com.sam.reggie.entity.Dish;
import com.sam.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
// 要点要继承
public class DishDto extends Dish {

  // 在Dish的基础上 扩展flavors
  private List<DishFlavor> flavors = new ArrayList<>();

  private String categoryName;

  private Integer copies;
}