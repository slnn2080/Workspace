package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sam.reggie.common.Result;
import com.sam.reggie.dto.SetmealDto;
import com.sam.reggie.entity.Category;
import com.sam.reggie.entity.Setmeal;
import com.sam.reggie.service.CategoryService;
import com.sam.reggie.service.SetmealDishService;
import com.sam.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

  @Autowired
  private SetmealService setmealService;

  @Autowired
  private SetmealDishService setmealDishService;

  @Autowired
  private CategoryService categoryService;


  // 处理 保存添加的套餐信息
  @PostMapping
  // 接收前端请求参数的形参 不能是 Setmeal实体类 因为还有一个setmealDishes属性并不在Setmeal实体类中 我们要使用SetmealDto
  public Result<String> save(@RequestBody SetmealDto setmealDto) {

    /*
      我们新增套餐的操作 需要向两张表中插入数据 所以我们扩展自己的service层来处理
      - setmeal
      - setmeal_dish
    */
    setmealService.saveSetmealAndSetmealDish(setmealDto);
    return Result.success("添加套餐成功");
  }

  @GetMapping("/page")
  public Result<Page> page(Integer page, Integer pageSize, String name) {

    // 查询setmeal表 获取分页数据
    Page<Setmeal> setmealPage = new Page<>(page, pageSize);
    LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
    setmealLambdaQueryWrapper
        .like(StringUtils.isNotBlank(name), Setmeal::getName, name)
        .orderByDesc(Setmeal::getUpdateTime);
    setmealService.page(setmealPage, setmealLambdaQueryWrapper);

    // 因为查询到的分页数据中没有 套餐分类(categoryName) 所以利用dto来扩展属性categoryName
    Page<SetmealDto> setmealDtoPage = new Page<>();

    // 将查询到的数据 除了records 复制到新的setmealDtoPage对象中
    BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");

    // 获取 setmealPage 有数据的 records
    List<Setmeal> records = setmealPage.getRecords();

    // 遍历records, 拿到categoryId 查询category表获取categoryName 并将records中的每一项Setmeal转换为SetmealDto, 并给每一个SetmealDto的categoryName进行赋值
    List<SetmealDto> list = records.stream().map(item -> {
      SetmealDto setmealDto = new SetmealDto();
      BeanUtils.copyProperties(item, setmealDto);
      Long categoryId = item.getCategoryId();
      Category category = categoryService.getById(categoryId);
      String categoryName = category.getName();
      setmealDto.setCategoryName(categoryName);
      return setmealDto;
    }).collect(Collectors.toList());

    setmealDtoPage.setRecords(list);

    return Result.success(setmealDtoPage);
  }


  @DeleteMapping
  /*
    ids参数 有两种情况
      - 163999
      - 163999,163999

    所以我们选择使用 List来承接, Spring会将 163999,163999 转换为 [163999,163999]

    使用 List 接收时 前面要加上 @RequestParam 注解
  */
  public Result<String> delete(@RequestParam List<Long> ids) {
    /*
      套餐删除逻辑:
        我们需要删除两张表中的关于套餐的信息, 套餐菜品关系表中的数据也要删掉
        1. setmeal
        2. setmeal_dish

      我们扩展自己service方法, 在service层中完成操作两张表的逻辑
    */
    setmealService.deleteSetmealAndSetmealDish(ids);
    return Result.success("删除套餐成功");
  }


  // 处理 停售 或 启售
  @PostMapping("/status/{status}")
  public Result<String> enable(@PathVariable Integer status, @RequestParam List<Long> ids) {
    LambdaUpdateWrapper<Setmeal> setmealLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
    setmealLambdaUpdateWrapper
        .in(Setmeal::getId, ids)
            .set(Setmeal::getStatus, status);

    setmealService.update(setmealLambdaUpdateWrapper);
    return Result.success("修改商品在售状态成功");
  }


  // 请求套餐数据的接口
  @GetMapping("/list")
  public Result<List<Setmeal>> list(Setmeal setmeal) {
    Long categoryId = setmeal.getCategoryId();
    Integer status = setmeal.getStatus();
    LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
    setmealLambdaQueryWrapper
        .eq(Setmeal::getCategoryId, categoryId)
        .eq(Setmeal::getStatus, status)
        .orderByDesc(Setmeal::getUpdateTime);
    List<Setmeal> list = setmealService.list(setmealLambdaQueryWrapper);
    return Result.success(list);
  }
}
