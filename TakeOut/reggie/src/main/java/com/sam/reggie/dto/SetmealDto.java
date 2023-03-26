package com.sam.reggie.dto;

import com.sam.reggie.entity.Setmeal;
import com.sam.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

  private List<SetmealDish> setmealDishes;

  private String categoryName;
}
