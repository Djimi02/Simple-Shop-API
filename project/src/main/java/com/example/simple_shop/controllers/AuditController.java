package com.example.simple_shop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.service.interfaces.AuditService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/audit")
@AllArgsConstructor
public class AuditController {
    
    private AuditService auditService;

    @GetMapping("/subscibers")
    public Long getTotalNumberOfSubscribers() {
        return auditService.getTotalNumberOfSubscribers();
    }

    @GetMapping("/soldproducts")
    public Long getNumberOfSoldProducts(
        @RequestParam(required = false, name = "date") Date date,
        @RequestParam(required = false, name = "isAvailable") Boolean isAvailable
     ) {
        return auditService.getNumberOfSoldProducts(date, isAvailable);
    }

    @GetMapping("/topproducts")
    public List<Product> getTopProducts(@RequestParam(required = false, name = "numOfProducts") Integer numOfProducts) {
        return auditService.getTopProducts(numOfProducts);
    }
    
}