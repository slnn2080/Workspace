package com.sam.reggie.controller;

import com.sam.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
  @Autowired
  private ShoppingCartService shoppingCartService;
}
