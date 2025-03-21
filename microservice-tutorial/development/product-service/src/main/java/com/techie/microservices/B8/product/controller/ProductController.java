package com.techie.microservices.B8.product.controller;

import com.techie.microservices.B8.product.dto.ProductRequest;
import com.techie.microservices.B8.product.dto.ProductResponse;
import com.techie.microservices.B8.product.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        // try{
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        return productService.getAllProducts();
    }
}
