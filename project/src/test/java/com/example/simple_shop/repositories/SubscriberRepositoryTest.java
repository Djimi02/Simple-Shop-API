package com.example.simple_shop.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simple_shop.entities.Subscriber;

@SpringBootTest
public class SubscriberRepositoryTest {

    @Autowired
    private SubscriberRepository repository;

    @Test
    public void testSaveAndDelete() {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName("fName");
        subscriber.setLastName("lName");
        subscriber.setJoinDate(new Date());

        assertTrue(subscriber.getId() == 0); // default long value

        subscriber = repository.save(subscriber);

        assertTrue(subscriber.getId() >= 1);

        repository.deleteById(subscriber.getId());

        try {
            repository.findById(subscriber.getId()).orElseThrow();
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}