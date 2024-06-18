package com.example.simple_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simple_shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}