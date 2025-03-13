package com.saga.microservices.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saga.microservices.order.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
