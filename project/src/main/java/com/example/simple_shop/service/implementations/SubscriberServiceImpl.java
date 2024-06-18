package com.example.simple_shop.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.repository.SubscriberRepository;
import com.example.simple_shop.service.interfaces.SubscriberService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
 
    private SubscriberRepository subRepo;

    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) {
        checkSubscriberFields(subscriber);
            
        // Assume initial subscriber does not have products yet
        subscriber.setSubscribedProducts(null);
        // Set current date
        subscriber.setJoinDate(new Date());
        return subRepo.save(subscriber);
    }

    @Override
    public Subscriber getSubscriberByID(Long subscriberID) {
        if (subscriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null!");
        }
        
        return subRepo.findById(subscriberID).orElseThrow(
            () -> new IllegalArgumentException(String.format("Subscriber with id = %2d does not exist!", subscriberID))
        );
    }

    @Override
    public void addProductToSubsciber(Long subscriberID, Long productID) {
        if (subscriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null!");
        } else if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProductToSubsciber'");
    }

    @Override
    public List<Subscriber> getSubsribersByProductID(Long productID) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }

        return subRepo.getSubsribersByProductID(productID);
    }

    @Transactional
    @Override
    public Subscriber updateSubscriber(Long subscriberID, Subscriber newSubscriberData) {
        if (subscriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null!");
        }

        checkSubscriberFields(newSubscriberData);

        Subscriber dbSub = subRepo.findById(subscriberID).orElseThrow(
            () -> new IllegalArgumentException(String.format("Subscriber with id = %2d does not exist!", subscriberID))
        );

        // Update data in db
        dbSub.setFirstName(newSubscriberData.getFirstName());
        dbSub.setLastName(newSubscriberData.getLastName());
        return dbSub;
    }

    @Override
    public void deleteSubscriberByID(Long subscriberID) {
        if (subscriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null!");
        } else if (!subRepo.existsById(subscriberID)) {
            throw new IllegalArgumentException(String.format("Subscriber with id = %2d does not exist!", subscriberID));
        }
        subRepo.deleteById(subscriberID);
    }

    private void checkSubscriberFields(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null!");
        } else if (subscriber.getFirstName() == null) {
            throw new IllegalArgumentException("Subscriber first name cannot be null!");
        } else if (subscriber.getLastName() == null) {
            throw new IllegalArgumentException("Subscriber last name cannot be null!");
        }
    }

}