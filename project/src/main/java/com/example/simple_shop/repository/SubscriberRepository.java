package com.example.simple_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simple_shop.entity.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    
}