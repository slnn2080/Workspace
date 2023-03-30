package com.sam.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sam.reggie.entity.Orders;


public interface OrdersService extends IService<Orders> {
  void submit(Orders orders);
}
