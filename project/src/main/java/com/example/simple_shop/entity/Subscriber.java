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
public class Subscriber {

    @Id
    @SequenceGenerator(name = "subscriberSeqGen", allocationSize = 1)
    @GeneratedValue(generator = "subscriberSeqGen")
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date joinDate;
    
}