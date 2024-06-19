package com.example.simple_shop.services.interfaces;

import java.util.List;

import com.example.simple_shop.entities.Product;
import com.example.simple_shop.entities.Subscriber;

public interface SubscriberService {
    
    /**
     * Saves a new subscriber into the database.
     * 
     * @param subscriber the Subscriber object to be saved.
     * @return the saved Subscriber object.
     * @throws IllegalArgumentException if the subscriber or any required field of the subscriber is null.
     */
    public Subscriber saveSubscriber(Subscriber subscriber);

    /**
     * Retrieves a subscriber by their ID.
     * 
     * @param subscriberID the ID of the subscriber to retrieve.
     * @return the Subscriber corresponding to the input id
     * @throws IllegalArgumentException if the subscriber ID is null or if no subscriber exists with the provided ID.
     */
    public Subscriber getSubscriberByID(Long subscriberID);
    
    /**
     * Adds a product to a subscriber's list of subscribed products. The Subscriber is also 
     * added to the list of subscribers of the specified product in the database.
     * 
     * @param subscriberID the ID of the subscriber to whom the product is to be added.
     * @param productID the ID of the product to add.
     * @throws IllegalArgumentException if either subscriberID or productID is null, or if they do not exist in the database.
     */
    public void addProductToSubscriber(Long subscriberID, Long productID);

    /**
     * Retrieves all products subscribed to by the subscriber specified with the input id.
     *
     * @param subscriberID the ID of the subscriber whose products are to be retrieved.
     * @return a list of Products subscribed to by the specified subscriber.
     * @throws IllegalArgumentException if the subscriber ID is null.
     */
    public List<Product> getProductsBySubscriberID(Long subsctriberID);

    /**
     * Updates the information of an existing subscriber in the database.
     * 
     * @param subscriberID the ID of the subscriber to update.
     * @param newSubscriberData the new data for the subscriber.
     * @return the updated Subscriber object.
     * @throws IllegalArgumentException if the subscriber ID is null or does not exist, or if the new subscriber data is invalid.
     */
    public Subscriber updateSubscriber(Long subscriberID, Subscriber newSubscriberData);

    /**
     * Deletes a subscriber from the database by their ID.
     * 
     * @param subscriberID the ID of the subscriber to delete.
     * @throws IllegalArgumentException if the subscriber ID is null or does not exist in the database.
     */
    public void deleteSubscriberByID(Long subscriberID);

}