package com.example.simple_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simple_shop.entity.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    
}