package com.example.simple_shop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.service.interfaces.SubscriberService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/subscribers")
@AllArgsConstructor
public class SubscriberController {
 
    private SubscriberService subscriberService;

    @PostMapping()
    public Subscriber saveSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.saveSubscriber(subscriber);
    }

    @GetMapping("/{subscriberID}")
    public Subscriber getSubscriberByID(@PathVariable("subscriberID") Long subscriberID) {
        return subscriberService.getSubscriberByID(subscriberID);
    }

    @PostMapping("/{subscriberID}/products/{productID}")
    public void addProductToSubscriber(@PathVariable("subscriberID") Long subscriberID, @PathVariable("productID") Long productID) {
        subscriberService.addProductToSubscriber(subscriberID, productID);
    }

    @GetMapping("/{subscriberID}/products")
    public List<Product> getProductsBySubscriberID(@PathVariable("subscriberID") Long subsctriberID) {
        return subscriberService.getProductsBySubscriberID(subsctriberID);
    }

    @PutMapping("/{subscriberID}")
    public Subscriber updateSubscriber(@PathVariable("subscriberID") Long subscriberID, @RequestBody Subscriber newSubscriberData) {
        return subscriberService.updateSubscriber(subscriberID, newSubscriberData);
    }

    @DeleteMapping("/{subscriberID}")
    public void deleteSubscriberByID(@PathVariable("subscriberID") Long subscriberID) {
        subscriberService.deleteSubscriberByID(subscriberID);
    }

}