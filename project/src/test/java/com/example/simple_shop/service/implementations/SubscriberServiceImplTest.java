package com.example.simple_shop.service.implementations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.service.interfaces.ProductService;
import com.example.simple_shop.service.interfaces.SubscriberService;

@SpringBootTest
public class SubscriberServiceImplTest {

    @Autowired
    private SubscriberService service;

    @Autowired
    private ProductService productService;

    @Test
    void testSaveSubscriberCorrect() {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");

        assertTrue(subscriber.getId() == 0);

        subscriber = service.saveSubscriber(subscriber);

        assertTrue(subscriber.getId() > 0);

        // Delete subscriber after test
        service.deleteSubscriberByID(subscriber.getId());
    }

    @Test
    void testSaveSubscriberIncorrect() {
        // Subscriber is null
        try {
            service.saveSubscriber(null);
        } catch (IllegalArgumentException e) { // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        // No first name
        try {
            Subscriber subscriber = new Subscriber();
            subscriber.setLastName("lName");

            service.saveSubscriber(subscriber);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        // No last name
        try {
            Subscriber subscriber = new Subscriber();
            subscriber.setFirstName("fName");

            service.saveSubscriber(subscriber);
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
        return service.saveSubscriber(subscriber);
    }

    @Test
    void testGetSubscriberByIDCorrect() {
        // First save subscriber
        Long subID = createAndSaveValidSubscriber().getId();

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
    void testAddProductToSubsciberCorrect() {
        Subscriber subscriber = createAndSaveValidSubscriber();

        Product product = createAndSaveValidProduct();

        // Initially empty
        assertTrue(product.getSubscibers().size() == 0);
        assertTrue(subscriber.getSubscribedProducts().size() == 0);

        service.addProductToSubscriber(subscriber.getId(), product.getId());

        Product dbProduct = productService.getProductByID(product.getId());
        Subscriber dbSub = service.getSubscriberByID(subscriber.getId());

        // Should map to each other
        assertTrue(dbProduct.getSubscibers().size() == 1);
        assertTrue(dbSub.getSubscribedProducts().size() == 1);

        // Delete product and subscriber after test
        service.deleteSubscriberByID(subscriber.getId());
        productService.deleteProductByID(product.getId());
    }

    @Test
    void testAddProductToSubsciberIncorrect() {
        // Adding non-existing product to non-existing subscriber
        try {
            service.addProductToSubscriber(-1l, -1l);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }
    }

    private Product createAndSaveValidProduct() {
        Product product = new Product();
        product.setName("pName");
        return productService.saveProduct(product);
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
    void testGetSubsribersByProductIDCorrect() {
        Product product = createAndSaveValidProduct();

        Subscriber subscriber1 = createAndSaveValidSubscriber();
        Subscriber subscriber2 = createAndSaveValidSubscriber();

        // Update data
        service.addProductToSubscriber(subscriber1.getId(), product.getId());
        service.addProductToSubscriber(subscriber2.getId(), product.getId());

        // Retrieve data from db
        List<Subscriber> subsribersByProductID = service.getSubsribersByProductID(product.getId());

        // Check
        assertTrue(subsribersByProductID.size() == 2);

        // Delete product and subscribers after test
        productService.deleteProductByID(product.getId());
        service.deleteSubscriberByID(subscriber1.getId());
        service.deleteSubscriberByID(subscriber2.getId());
    }

    @Test
    void testGetSubsribersByProductIDIncorrect() {
        // Null product id
        try {
            service.getSubsribersByProductID(null);
            // Should have thrown exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }

        // Testing with non-existing product
        List<Subscriber> subsribersByProductID = service.getSubsribersByProductID(-1l);
        assertTrue(subsribersByProductID.size() == 0);
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