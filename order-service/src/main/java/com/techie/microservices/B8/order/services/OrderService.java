package com.techie.microservices.B8.order.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.techie.microservices.B8.order.client.InventoryClient;
import com.techie.microservices.B8.order.dto.OrderRequest;
import com.techie.microservices.B8.order.model.Order;
import com.techie.microservices.B8.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest ordereRequest) {


        //1. Using Mockito
        //2. Use Wiremock
        var isProductInStock = inventoryClient.isInStock(ordereRequest.skuCode(), ordereRequest.quantity());

        if (isProductInStock) {
            // map OrderRequest
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(ordereRequest.price());
            order.setSkuCode(ordereRequest.skuCode());
            order.setQuantity(ordereRequest.quantity());
            // save OrderRepository
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode " + ordereRequest.skuCode() + " is not in stock");
        }
    }
}
