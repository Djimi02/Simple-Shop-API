package com.example.simple_shop.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.repository.ProductRepository;
import com.example.simple_shop.repository.SubscriberRepository;
import com.example.simple_shop.service.interfaces.AuditService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private SubscriberRepository subscriberRepository;
    
    private ProductRepository productRepository;

    @Override
    public Long getTotalNumberOfSubscribers() {
        return subscriberRepository.count();
    }

    @Override
    public Long getNumberOfSoldProducts(Date date, Boolean isAvailable) {
        return productRepository.getNumberOfProductsByDateAndAvailability(date, isAvailable);
    }

    @Override
    public List<Product> getTopProducts(Integer numOfProducts) {
        if (numOfProducts == null) {
            numOfProducts = 5; // Default size
        }
        return productRepository.getTopProducts(PageRequest.of(0, numOfProducts));
    }
    
}