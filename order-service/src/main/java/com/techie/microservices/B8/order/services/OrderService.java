package com.techie.microservices.B8.order.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.techie.microservices.B8.order.dto.OrderRequest;
import com.techie.microservices.B8.order.model.Order;
import com.techie.microservices.B8.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest ordereRequest) {
        // map OrderRequest
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(ordereRequest.price());
        order.setSkuCode(ordereRequest.skuCode());
        order.setQuantity(ordereRequest.quantity());
        // save OrderRepository
        orderRepository.save(order);
    
    }
}
