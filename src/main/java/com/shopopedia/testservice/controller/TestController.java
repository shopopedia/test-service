package com.shopopedia.testservice.controller;

import com.shopopedia.testservice.entity.Product;
import com.shopopedia.testservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/health")
    public String health() {
        return "test-service running";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return repository.findAll();
    }
}