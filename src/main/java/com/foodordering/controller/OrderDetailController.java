package com.foodordering.controller;


import com.foodordering.service.OrderDetailService;
import com.foodordering.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrdersService ordersService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, OrdersService ordersService){
        this.orderDetailService=orderDetailService;
        this.ordersService=ordersService;
    }

}