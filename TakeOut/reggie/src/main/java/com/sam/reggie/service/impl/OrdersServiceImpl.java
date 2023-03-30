package com.sam.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sam.reggie.common.BaseContext;
import com.sam.reggie.common.CustomException;
import com.sam.reggie.entity.*;
import com.sam.reggie.mapper.OrdersMapper;
import com.sam.reggie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

  @Autowired
  private ShoppingCartService shoppingCartService;
  @Autowired
  private UserService userService;
  @Autowired
  private AddressBookService addressBookService;
  @Autowired
  private OrderDetailService orderDetailService;

  @Override
  @Transactional
  public void submit(Orders orders) {
    // 1. 获取当前的用户id
    Long userId = BaseContext.getCurrentId();

    // 2. 根据用户id查询购物车表 查询当前用户的购物车数据
    LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
    shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
    // 获取购物车中的数据 是一个集合
    List<ShoppingCart> shoppingCarts = shoppingCartService.list(shoppingCartLambdaQueryWrapper);

    // 如果购物车为空 可以不用下单了
    if(shoppingCarts == null || shoppingCarts.size() == 0) {
      throw new CustomException("购物车为空 不能下单");
    }

    // 3. 向订单表插入一条数据(不管我们买了多少菜品)
    /*
      订单表 非空字段
      - status
      - user_id
      - address_book_id
      - order_time
      - checkout_time
      - pay_method
      - amount

      根据用户id
        查询用户表 获取用户信息 订单表中需要
        查询地址簿 获取用户地址信息
    */

    // 查询用户数据
    User user = userService.getById(userId);

    // 查询地址数据, 根据前端发送的 addressBookId 查询
    AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());

    // 判断
    if(addressBook == null) {
      throw new CustomException("地址信息有误 不能下单");
    }

    // 装配orders对象
    // 设置用户id
    orders.setUserId(userId);
    // 生成订单号
    long orderNumber = IdWorker.getId();
    orders.setNumber(String.valueOf(orderNumber));

    // 设置订单id 我们也可以使用订单号
    orders.setId(orderNumber);

    // 设置下单订单时间
    orders.setOrderTime(LocalDateTime.now());

    // 设置结账时间
    orders.setCheckoutTime(LocalDateTime.now());

    // 设置订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    orders.setStatus(2);

    // 设置订单的总金额: 我们需要将订单明细中的一条条数据的金额汇总 自己计算
    // 使用AtomicInteger 将菜品和套餐的价格累加到amount变量上, 它可以保证在多线程的情况下 计算也不会出现问题, 如果我们使用的是普通的int 在高并发的情况下就会出现计算错误的情况
    AtomicInteger amount = new AtomicInteger(0);
    // 遍历购物车中的数据(同时也在封装订单明细表中需要的数据)
    List<OrderDetail> orderDetails = shoppingCarts.stream().map(item -> {
      // 创建 OrderDetail 对象
      OrderDetail orderDetail = new OrderDetail();
      orderDetail.setOrderId(orderNumber);
      // 设置菜品的数量
      orderDetail.setNumber(item.getNumber());
      orderDetail.setDishFlavor(item.getDishFlavor());
      orderDetail.setDishId(item.getDishId());
      orderDetail.setSetmealId(item.getSetmealId());
      orderDetail.setName(item.getName());
      orderDetail.setImage(item.getImage());
      orderDetail.setAmount(item.getAmount());

      // 使用AtomicInteger对象来计算金额 addAndGet相当于+=
      amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());

      return orderDetail;
    }).collect(Collectors.toList());

    // 设置总金额
    orders.setAmount(new BigDecimal(amount.get()));

    // 设置用户名
    orders.setUserName(user.getName());

    // 设置收货人
    orders.setConsignee(addressBook.getConsignee());

    // 设置收货人电话
    orders.setPhone(addressBook.getPhone());

    // 添加地址
    orders.setAddress(
        (addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName()) +
        (addressBook.getCityName() == null ? "" : addressBook.getCityName()) +
        (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName()) +
        (addressBook.getDetail() == null ? "" : addressBook.getDetail())
    );

    // 保存订单
    this.save(orders);


    // 4. 向订单明细表插入数据, 有可能是多条数据 (我们买了几个菜品 就插入几条数据)
    /*
      订单明细表 非空字段
      - order_id
      - number
      - amount
    */
    // 批量插入
    orderDetailService.saveBatch(orderDetails);

    // 5. 下单完成后 清空购物车数据
    // shoppingCartLambdaQueryWrapper 上面已经封装上 userId 了 直接使用就可以
    shoppingCartService.remove(shoppingCartLambdaQueryWrapper);
  }
}
