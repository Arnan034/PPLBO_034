package com.saga.microservices.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.saga.microservices.commons.dto.OrderRequestDto;
import com.saga.microservices.order.entity.PurchaseOrder;
import com.saga.microservices.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderService.getAllOrders();
    }
}
