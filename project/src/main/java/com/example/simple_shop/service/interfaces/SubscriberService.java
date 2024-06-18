package com.example.simple_shop.service.interfaces;

import java.util.List;

import com.example.simple_shop.entity.Subscriber;

public interface SubscriberService {
    
    public Subscriber saveSubscriber(Subscriber subscriber);

    public Subscriber getSubscriberByID(Long subscriberID);
    
    public void addProductToSubscriber(Long subscriberID, Long productID);

    public List<Subscriber> getSubsribersByProductID(Long productID);

    public Subscriber updateSubscriber(Long subscriberID, Subscriber newSubscriberData);

    public void deleteSubscriberByID(Long subscriberID);

    public boolean subscriberExistsByID(Long subscriberID);

}