package com.techie.microservices.B8.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.techie.microservices.B8.order.services.OrderService;
import com.techie.microservices.B8.order.dto.OrderRequest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("Place Order Request: {}", orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }


}
