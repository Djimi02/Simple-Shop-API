package com.example.simple_shop.service.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.repository.SubscriberRepository;
import com.example.simple_shop.service.interfaces.ProductService;
import com.example.simple_shop.service.interfaces.SubscriberService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
 
    private SubscriberRepository subRepo;

    private ProductService productService;

    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) {
        checkSubscriberFields(subscriber);
            
        // Assume initial subscriber does not have products yet
        subscriber.setSubscribedProducts(new ArrayList<>());
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
    public void addProductToSubscriber(Long subscriberID, Long productID) {
        if (subscriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null!");
        } else if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }

        Product productDB = productService.getProductByID(productID);

        Subscriber subscriberDB = getSubscriberByID(subscriberID);

        // Update data in db
        subscriberDB.getSubscribedProducts().add(productDB);
        subRepo.save(subscriberDB);
    }

    @Override
    public List<Subscriber> getSubsribersByProductID(Long productID) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }

        return subRepo.getSubsribersByProductID(productID);
    }

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
        return subRepo.save(dbSub);
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

    @Override
    public boolean subscriberExistsByID(Long subscriberID) {
        if (subscriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null!");
        }

        return subRepo.existsById(subscriberID);
    }

}