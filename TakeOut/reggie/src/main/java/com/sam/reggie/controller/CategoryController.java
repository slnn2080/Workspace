package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sam.reggie.common.Result;
import com.sam.reggie.entity.Category;
import com.sam.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public Result<String> save(@RequestBody Category category) {
    categoryService.save(category);
    return Result.success("添加成功");
  }

  @GetMapping("/page")
  public Result<Page<Category>> page(Integer page, Integer pageSize) {
    Page<Category> pageInfo = new Page<>(page, pageSize);
    LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.orderByDesc(Category::getSort);
    categoryService.page(pageInfo, queryWrapper);

    return Result.success(pageInfo);
  }


  // 根据id删除分类
  @DeleteMapping
  // url携带的id 所以我们直接定义形参就可以
  // 前端判断需要的是code 所以返回值中我们传入 String 就可以
  public Result<String> deleteCategory(Long id) {

    // 调用我们service层中自定义的方法 内部会处理判断逻辑
    categoryService.remove(id);
    return Result.success("删除分类成功");
  }


  // 修改菜品的处理
  @PutMapping
  public Result<String> updateCategory(@RequestBody Category category) {
    categoryService.updateById(category);
    return Result.success("修改分类成功");
  }


  // 根据条件查询分类数据: 处理查询 菜品分类 数据的请求
  @GetMapping("/list")
  // 参数的位置 我们可以使用 String type 来接收, 也可以定义为实体类来接收参数, 实体类的方式更好 因为更加的通用 如果后期扩展了其他参数 用实体类封装更好
  public Result<List<Category>> categoryList(Category category) {

    LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
    // 判断下 type是否为空
    categoryLambdaQueryWrapper
        .eq(category.getType() != null,  Category::getType, category.getType())
        .orderByAsc(Category::getSort)
        .orderByDesc(Category::getUpdateTime);

    List<Category> list = categoryService.list(categoryLambdaQueryWrapper);

    return Result.success(list);

  }
}
