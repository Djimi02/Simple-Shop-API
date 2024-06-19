package com.example.simple_shop.service.interfaces;

import java.util.List;

import com.example.simple_shop.entity.Product;

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
     * Retrieves all products subscribed to by the subscriber specified with the input id.
     *
     * @param subscriberID the ID of the subscriber whose products are to be retrieved.
     * @return a list of Products subscribed to by the specified subscriber.
     * @throws IllegalArgumentException if the subscriber ID is null.
     */
    public List<Product> getProductsBySubscriberID(Long subsctriberID);

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