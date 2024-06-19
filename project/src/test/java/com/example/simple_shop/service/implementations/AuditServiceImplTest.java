package com.example.simple_shop.service.implementations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.service.interfaces.AuditService;
import com.example.simple_shop.service.interfaces.ProductService;
import com.example.simple_shop.service.interfaces.SubscriberService;

@SpringBootTest
public class AuditServiceImplTest {

    @Autowired
    private AuditService auditService;

    @Autowired 
    private ProductService productService;

    @Autowired
    private SubscriberService subscriberService;

    @Test
    void testGetNumberOfSoldProducts() {
        Product product1 = createAndSaveValidProduct(false);
        Product product2 = createAndSaveValidProduct(false);
        Product product3 = createAndSaveValidProduct(true);

        // Check total count without filter
        assertTrue(auditService.getNumberOfSoldProducts(null, null) == 3);
        // Check count with availability filter
        assertTrue(auditService.getNumberOfSoldProducts(null, true) == 1);
        assertTrue(auditService.getNumberOfSoldProducts(null, false) == 2);

        // Delete subscibers after test
        productService.deleteProductByID(product1.getId());
        productService.deleteProductByID(product2.getId());
        productService.deleteProductByID(product3.getId());
    }

    @Test
    void testGetTopProducts() {
        Product product1 = createAndSaveValidProduct(false); // top prod
        Product product2 = createAndSaveValidProduct(false); // middle prod
        Product product3 = createAndSaveValidProduct(true); // bottom prod

        Subscriber subscriber1 = createAndSaveValidSubscriber();
        Subscriber subscriber2 = createAndSaveValidSubscriber();

        // Adding 2 subscribers to product1
        subscriberService.addProductToSubscriber(subscriber1.getId(), product1.getId());
        subscriberService.addProductToSubscriber(subscriber2.getId(), product1.getId());

        // Adding 1 subscriber to product2
        subscriberService.addProductToSubscriber(subscriber1.getId(), product2.getId());

        List<Product> topProducts = auditService.getTopProducts(null);
        // Product3 will not be returned as it has no subscribers
        assertTrue(topProducts.size() == 2);

        // Checking for correct order
        assertTrue(topProducts.get(0).getId() == product1.getId());
        assertTrue(topProducts.get(1).getId() == product2.getId());

        // Delete products and subscribers after test
        productService.deleteProductByID(product1.getId());
        productService.deleteProductByID(product2.getId());
        productService.deleteProductByID(product3.getId());
        subscriberService.deleteSubscriberByID(subscriber1.getId());
        subscriberService.deleteSubscriberByID(subscriber2.getId());
    }

    @Test
    void testGetTotalNumberOfSubscribers() {
        Subscriber subscriber1 = createAndSaveValidSubscriber();
        Subscriber subscriber2 = createAndSaveValidSubscriber();

        assertTrue(auditService.getTotalNumberOfSubscribers() == 2);

        // Delete subscribers after test
        subscriberService.deleteSubscriberByID(subscriber1.getId());
        subscriberService.deleteSubscriberByID(subscriber2.getId());
    }

    private Product createAndSaveValidProduct(boolean isAvailable) {
        Product product = new Product();
        product.setIsAvailable(isAvailable);
        product.setName("pName");
        return productService.saveProduct(product);
    }

    private Subscriber createAndSaveValidSubscriber() {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");
        return subscriberService.saveSubscriber(subscriber);
    }

}