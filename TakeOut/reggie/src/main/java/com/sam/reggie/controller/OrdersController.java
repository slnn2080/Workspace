package com.sam.reggie.controller;

import com.sam.reggie.common.Result;
import com.sam.reggie.entity.Orders;
import com.sam.reggie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrdersController {

  @Autowired
  private OrdersService ordersService;

  @PostMapping("submit")
  public Result<String> submit(@RequestBody Orders orders) {
    ordersService.submit(orders);
    return Result.success("下单成功");
  }
}
