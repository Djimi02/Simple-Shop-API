package com.example.simple_shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.simple_shop.entities.Product;
import com.example.simple_shop.entities.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query("SELECT p FROM Product p JOIN p.subscibers s WHERE s.id = :subsctriberID")
    public List<Product> getProductsBySubscriberID(@Param("subsctriberID") Long subsctriberID);
    
}