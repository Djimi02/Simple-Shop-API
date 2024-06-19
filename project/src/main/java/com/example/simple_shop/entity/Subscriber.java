package com.example.simple_shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date joinDate;

    @JsonIgnoreProperties({"subscibers"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "subscriber_product", 
        joinColumns = @JoinColumn(name = "subscriber_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> subscribedProducts = new ArrayList<>();
    
}