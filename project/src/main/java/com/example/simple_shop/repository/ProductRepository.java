package com.example.simple_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.simple_shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p JOIN p.subscibers s WHERE s.id = :subsctriberID")
    public List<Product> getProductsBySubscriberID(@Param("subsctriberID") Long subsctriberID);

}