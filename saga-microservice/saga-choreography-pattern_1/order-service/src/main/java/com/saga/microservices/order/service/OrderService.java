package com.saga.microservices.order.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.saga.microservices.commons.dto.OrderRequestDto;
import com.saga.microservices.commons.event.OrderStatus;
import com.saga.microservices.order.entity.PurchaseOrder;
import com.saga.microservices.order.repository.OrderRepository;
import java.util.List;

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusPublisher orderStatusPublisher;
    
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getId());
        // product kafka event with status oerder_created
        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return order;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    private PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(dto.getAmount());
        return purchaseOrder;
    }
}
