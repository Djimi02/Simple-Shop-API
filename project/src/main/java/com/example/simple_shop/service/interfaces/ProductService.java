package com.example.simple_shop.service.interfaces;

import java.util.List;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;

public interface ProductService {
 
    /**
     * Saves a new product into the database.
     *
     * @param product the Product object to be saved.
     * @return the saved Product object.
     * @throws IllegalArgumentException if the product or any of its mandatory fields is null.
     */
    public Product saveProduct(Product product);

    /**
     * Retrieves a product by its ID.
     *
     * @param productID the ID of the product to be retrieved.
     * @return the Product object corresponding to the specified id
     * @throws IllegalArgumentException if the product ID is null or if no product exists with the given ID.
     */
    public Product getProductByID(Long productID);

    /**
     * Retrieves all subscribers subscribed to a particular product.
     * 
     * @param productID the ID of the product whose subscribers are to be retrieved.
     * @return a list of Subscribers who are subscribed to the specified product.
     * @throws IllegalArgumentException if the product ID is null.
     */
    public List<Subscriber> getSubsribersByProductID(Long productID);

    /**
     * Updates an existing product in the database.
     *
     * @param productID the ID of the product to update.
     * @param newProductData the new data for the product.
     * @return the updated Product object.
     * @throws IllegalArgumentException if the product ID is null, does not exist, or if the new product data is invalid.
     */
    public Product updateProduct(Long productID, Product newProductData);

    /**
     * Deletes a product from the database by its ID.
     *
     * @param productID the ID of the product to be deleted.
     * @throws IllegalArgumentException if the product ID is null or does not exist in the database.
     */
    public void deleteProductByID(Long productID);

}