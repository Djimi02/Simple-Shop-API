package com.example.simple_shop.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
 
    @Id
    @SequenceGenerator(name = "productSeqGen", allocationSize = 1)
    @GeneratedValue(generator = "productSeqGen")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private boolean isAvailable;

}