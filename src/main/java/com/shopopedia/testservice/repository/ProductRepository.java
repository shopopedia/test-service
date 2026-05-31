package com.shopopedia.testservice.repository;

import com.shopopedia.testservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}