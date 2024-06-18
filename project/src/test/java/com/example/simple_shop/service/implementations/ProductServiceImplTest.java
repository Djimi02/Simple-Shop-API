package com.example.simple_shop.service.implementations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.service.interfaces.ProductService;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService service;

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
    void testGetProductsBySubscriberID() {

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