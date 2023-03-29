package com.sam.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.sam.reggie.common.BaseContext;
import com.sam.reggie.common.Result;
import com.sam.reggie.entity.AddressBook;
import com.sam.reggie.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

  @Autowired
  private AddressBookService addressBookService;

  /**
   * 新增一个地址簿
   * 请求地址: addressBook
   * 请求方式: Post
   * 请求参数:
   *    consignee: 刘小明
   *    detail: 天河区
   *    label: 公司
   *    phone: 13090909090
   *    sex: 1
   */
  @PostMapping
  public Result<AddressBook> save(@RequestBody AddressBook addressBook) {
    // 前端请求参数中并没有 user_id 信息(这个地址是谁的) 所以我们需要手动设置, 从ThreadLocal中取出(用户登录成功后 就可以通过它来获取用户id)
    addressBook.setUserId(BaseContext.getCurrentId());
    log.info("addressBook:{}", addressBook);
    addressBookService.save(addressBook);
    return Result.success(addressBook);
  }

  /**
   * 将某个地址信息 设置默认地址
   * 请求地址: /default
   * 请求参数: id:"1641068552637526017"
   */
  @PutMapping("default")
  public Result<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
    log.info("addressBook:{}", addressBook);

    /*
      用户可以有多个地址, 但只能有一个是默认地址
      所以我们的修改逻辑为: 类似js的排他思想, 循环修改为默认值 然后再修改目标值
        1. 先将该用户(user_id)下所有的地址记录的is_default字段修改为0
        2. 然后利用形参的 addressBook 的 isDefault 设置为 1
        3. 保存到数据库
    */
    LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
    wrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
    wrapper.set(AddressBook::getIsDefault, 0);
    //SQL:update address_book set is_default = 0 where user_id = ?
    addressBookService.update(wrapper);

    addressBook.setIsDefault(1);
    // 根据地址簿表记录的id 修改该记录的默认地址情况
    //SQL:update address_book set is_default = 1 where id = ?
    addressBookService.updateById(addressBook);
    return Result.success(addressBook);
  }

  /**
   * 根据id查询地址
   */
  @GetMapping("/{id}")
  public Result get(@PathVariable Long id) {
    AddressBook addressBook = addressBookService.getById(id);
    if (addressBook != null) {
      return Result.success(addressBook);
    } else {
      return Result.error("没有找到该对象");
    }
  }

  /**
   * 查询默认地址
   */
  @GetMapping("default")
  public Result<AddressBook> getDefault() {
    LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
    queryWrapper.eq(AddressBook::getIsDefault, 1);

    //SQL:select * from address_book where user_id = ? and is_default = 1
    AddressBook addressBook = addressBookService.getOne(queryWrapper);

    if (null == addressBook) {
      return Result.error("没有找到该对象");
    } else {
      return Result.success(addressBook);
    }
  }

  /**
   * 查询指定用户的全部地址
   */
  @GetMapping("/list")
  public Result<List<AddressBook>> list(AddressBook addressBook) {
    addressBook.setUserId(BaseContext.getCurrentId());
    log.info("addressBook:{}", addressBook);

    //条件构造器
    LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(null != addressBook.getUserId(), AddressBook::getUserId, addressBook.getUserId());
    queryWrapper.orderByDesc(AddressBook::getUpdateTime);

    //SQL:select * from address_book where user_id = ? order by update_time desc
    return Result.success(addressBookService.list(queryWrapper));
  }
}
