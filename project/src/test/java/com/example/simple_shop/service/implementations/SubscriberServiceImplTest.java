package com.example.simple_shop.service.implementations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.service.interfaces.SubscriberService;

@SpringBootTest
public class SubscriberServiceImplTest {

    @Autowired
    private SubscriberService service;

    @Test
    void testAddSubscriberCorrect() {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");

        assertTrue(subscriber.getId() == 0);

        subscriber = service.addSubscriber(subscriber);

        assertTrue(subscriber.getId() > 0);

        // Delete subscriber after test
        service.deleteSubscriberByID(subscriber.getId());
    }

    @Test
    void testAddSubscriberIncorrect() {
        // Subscriber is null
        try {
            service.addSubscriber(null);
        } catch (IllegalArgumentException e) { // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        // No first name
        try {
            Subscriber subscriber = new Subscriber();
            subscriber.setLastName("lName");

            service.addSubscriber(subscriber);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        // No last name
        try {
            Subscriber subscriber = new Subscriber();
            subscriber.setFirstName("fName");

            service.addSubscriber(subscriber);
        } catch (IllegalArgumentException e) { // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    private Subscriber createAndSaveValidSubscriber() {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");
        return service.addSubscriber(subscriber);
    }

    @Test
    void testGetSubscriberByIDCorrect() {
        // First save subscriber
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");
        Long subID = service.addSubscriber(subscriber).getId();

        try {
            service.getSubscriberByID(subID);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        // Delete subscriber after test
        service.deleteSubscriberByID(subID);
    }

    @Test
    void testGetSubscriberByIDIncorrect() {
        try {
            service.getSubscriberByID(-1l);
            assertTrue(false);
        } catch (IllegalArgumentException e) { // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testAddProductToSubsciber() {
        
    }

    @Test
    void testDeleteSubscriberByIDCorrect() {
        // First save subscriber
        Long subID = createAndSaveValidSubscriber().getId();

        try {
            service.deleteSubscriberByID(subID);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteSubscriberByIDIncorrect() {
        try {
            service.deleteSubscriberByID(-1l);
            assertTrue(false);
        } catch (IllegalArgumentException e) { // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testGetSubsribersByProductID() {

    }

    @Test
    void testUpdateSubscriberCorrect() {
        // First save subscriber
        Long subID = createAndSaveValidSubscriber().getId();

        // Create new data
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setFirstName("fName1");
        subscriber2.setLastName("lName1");

        service.updateSubscriber(subID, subscriber2);

        Subscriber dbSub = service.getSubscriberByID(subID);
        assertTrue(dbSub.getFirstName().equals("fName1"));
        assertTrue(dbSub.getLastName().equals("lName1"));

        // Delete subscriber after test
        service.deleteSubscriberByID(subID);
    }

    @Test
    void testUpdateSubscriberIncorrect() {
        // Updating non-existing subscriber
        try {
            // Create new data
            Subscriber subscriber2 = new Subscriber();
            subscriber2.setFirstName("fName1");
            subscriber2.setLastName("lName1");

            service.updateSubscriber(-1l, subscriber2);
            // Should throw exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }

        // Updating with wrong data (no first name)
        Long subID = null;
        try {
            // First save subscriber
            subID = createAndSaveValidSubscriber().getId();

            // Create new data
            Subscriber subscriber2 = new Subscriber();
            subscriber2.setLastName("lName1");

            service.updateSubscriber(subID, subscriber2);
            // Should throw exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }
        // Delete after test
        service.deleteSubscriberByID(subID);
    }
}