package com.example.simple_shop.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simple_shop.entity.Product;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void testSaveAndDelete() {
        Product product = new Product();
        product.setName("pName");
        product.setCreationDate(new Date());
        product.setAvailable(false);

        assertTrue(product.getId() == 0); // default long value

        product = repository.save(product);

        assertTrue(product.getId() > 0);

        repository.deleteById(product.getId());

        try {
            repository.findById(product.getId()).orElseThrow();
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}