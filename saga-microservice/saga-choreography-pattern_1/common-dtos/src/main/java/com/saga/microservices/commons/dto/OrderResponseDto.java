package com.saga.microservices.commons.dto;

import com.saga.microservices.commons.event.OrderStatus;

public class OrderResponseDto {
    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
