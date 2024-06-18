package com.example.simple_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.simple_shop.entity.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query("SELECT s FROM Subscriber s JOIN s.subscribedProducts p WHERE p.id = :productID")
    List<Subscriber> getSubsribersByProductID(@Param("productID") Long productID);
    
}