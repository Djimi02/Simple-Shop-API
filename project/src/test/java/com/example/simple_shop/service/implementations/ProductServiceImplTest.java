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
public class ProductServiceImplTest {

    @Autowired
    private ProductService service;

    @Autowired
    private SubscriberService subscriberService;

    @Test
    void testSaveProductCorrect() {
        Product product = new Product();
        product.setAvailable(false);
        product.setName("pName");

        assertTrue(product.getId() == 0);

        product = service.saveProduct(product);

        assertTrue(product.getId() > 0);

        // Delete product after test
        service.deleteProductByID(product.getId());
    }

    @Test
    void testSaveProductIncorrect() {
        // Saving null product
        try {
            service.saveProduct(null);
            // Exception should have been thrown
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Wrong exception
            assertTrue(false);
        }

        // Saving product with null name
        try {
            service.saveProduct(new Product());
            // Exception should have been thrown
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Wrong exception
            assertTrue(false);
        }
    }

    private Product createAndSaveValidProduct() {
        Product product = new Product();
        product.setAvailable(false);
        product.setName("pName");
        return service.saveProduct(product);
    }

    @Test
    void testGetProductByIDCorrect() {
        Long prodID = createAndSaveValidProduct().getId();

        try {
            service.getProductByID(prodID);
            // Should not throw exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        // Delete product after test
        service.deleteProductByID(prodID);
    }

    @Test
    void testGetProductByIDIncorrect() {
        try {
            service.getProductByID(-1l);
            // Should have thrown exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }
    }

    @Test
    void testGetSubsribersByProductIDCorrect() {
        Product product = createAndSaveValidProduct();

        Subscriber subscriber1 = createAndSaveValidSubscriber();
        Subscriber subscriber2 = createAndSaveValidSubscriber();

        // Update data
        subscriberService.addProductToSubscriber(subscriber1.getId(), product.getId());
        subscriberService.addProductToSubscriber(subscriber2.getId(), product.getId());

        // Retrieve data from db
        List<Subscriber> subsribersByProductID = service.getSubsribersByProductID(product.getId());

        // Check
        assertTrue(subsribersByProductID.size() == 2);

        // Delete product and subscribers after test
        service.deleteProductByID(product.getId());
        subscriberService.deleteSubscriberByID(subscriber1.getId());
        subscriberService.deleteSubscriberByID(subscriber2.getId());
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

    private Subscriber createAndSaveValidSubscriber() {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");
        return subscriberService.saveSubscriber(subscriber);
    }

    @Test
    void testUpdateProductCorrect() {
        // Save product first
        Long prodID = createAndSaveValidProduct().getId();

        // Create new data
        Product product2 = new Product();
        product2.setName("pName2");

        service.updateProduct(prodID, product2);

        Product dbProd = service.getProductByID(prodID);
        assertTrue(dbProd.getName().equals(product2.getName()));

        // Delete product after test
        service.deleteProductByID(prodID);
    }

    @Test
    void testUpdateProductIncorrect() {
        // Save product first
        Long prodID = createAndSaveValidProduct().getId();

        // Updating non-existing product
        try {
            // Create new data
            Product product2 = new Product();
            product2.setName("pName2");

            service.updateProduct(-1l, product2);
            // Should have thrown exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }

        // Updating with wrong data (no product name)
        try {
            // Create new data
            Product product2 = new Product();

            service.updateProduct(prodID, product2);
            // Should have thrown exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }

        // Delete product after test
        service.deleteProductByID(prodID);
    }

    @Test
    void testDeleteProductByIDCorrect() {
        // Save product first
        Long prodID = createAndSaveValidProduct().getId();

        try {
            service.deleteProductByID(prodID);
            // Should not throw exception
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testDeleteProductByIDIncorrect() {
        try {
            service.deleteProductByID(-1l);
            // Should throw exception
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            // Correct exception
            assertTrue(true);
        } catch (Exception e) {
            // Incorrect exception
            assertTrue(false);
        }
    }
}