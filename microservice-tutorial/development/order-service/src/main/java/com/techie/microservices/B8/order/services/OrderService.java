package com.techie.microservices.B8.order.services;

import com.techie.microservices.B8.order.client.InventoryClient;
import com.techie.microservices.B8.order.dto.OrderRequest;
import com.techie.microservices.B8.order.event.OrderPlacedEvent;
import com.techie.microservices.B8.order.model.Order;
import com.techie.microservices.B8.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            // map OrderRequest
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())));
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);

            // Send the message to Kafka Topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
            log.info("Start - Sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed-topic", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);

            return "Order Placed Successfully";
        } else {
            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }
    }
}
