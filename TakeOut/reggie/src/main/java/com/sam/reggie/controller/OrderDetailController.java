package com.sam.reggie.controller;

import com.sam.reggie.entity.OrderDetail;
import com.sam.reggie.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

  @Autowired
  private OrderDetailService orderDetailService;
}
