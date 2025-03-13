package com.saga.microservices.payment.repository;

import com.saga.microservices.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {

}
