package com.example.simple_shop.entities;

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
import jakarta.persistence.ManyToMany;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Boolean isAvailable;

    @JsonIgnoreProperties({"subscribedProducts"})
    @ManyToMany(mappedBy = "subscribedProducts", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Subscriber> subscibers = new ArrayList<>();

}