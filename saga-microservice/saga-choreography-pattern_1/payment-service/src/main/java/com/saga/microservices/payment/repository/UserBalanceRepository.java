package com.saga.microservices.payment.repository;

import com.saga.microservices.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {

}
