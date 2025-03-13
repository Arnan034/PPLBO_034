package com.techie.microservices.B8.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techie.microservices.B8.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
