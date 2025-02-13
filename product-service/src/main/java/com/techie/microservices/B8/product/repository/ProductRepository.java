package com.techie.microservices.B8.product.repository;

import com.techie.microservices.B8.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{
}
