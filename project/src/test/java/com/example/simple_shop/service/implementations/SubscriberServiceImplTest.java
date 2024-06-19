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
        product.setIsAvailable(true);
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
    void testGetProductsBySubscriberIDCorrect() {
        Subscriber subscriber = createAndSaveValidSubscriber();

        Product product1 = createAndSaveValidProduct();
        Product product2 = createAndSaveValidProduct();

        // Update data
        service.addProductToSubscriber(subscriber.getId(), product1.getId());
        service.addProductToSubscriber(subscriber.getId(), product2.getId());

        // Retrieve data
        List<Product> productsBySubscriberID = service.getProductsBySubscriberID(subscriber.getId());

        // Check
        assertTrue(productsBySubscriberID.size() == 2);

        // Delete products and subscriber after test
        productService.deleteProductByID(product1.getId());
        productService.deleteProductByID(product2.getId());
        service.deleteSubscriberByID(subscriber.getId());
    }

    @Test
    void testGetProductsBySubscriberIDIncorrect() {
        // Null product id
        try {
            service.getProductsBySubscriberID(null);
            // Should have thrown exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }

        // Test with non-existing subscriber
        List<Product> productsBySubscriberID = service.getProductsBySubscriberID(-1l);
        assertTrue(productsBySubscriberID.size() == 0);
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