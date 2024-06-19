package com.example.simple_shop.services.interfaces;

import java.util.Date;
import java.util.List;

import com.example.simple_shop.entities.Product;

public interface AuditService {
 
    /**
     * Retrieves the total number of subscribers in the database.
     *
     * @return the count of all subscribers in the database.
     */
    public Long getTotalNumberOfSubscribers();

    /**
     * Retrieves the number of products based on their creation date and availability.
     * 
     * @param date the date for which the products are created.
     * @param isAvailable the availability of the product.
     * @return the count of products that were created on the specified date and
     * match the specified availability.
     */
    public Long getNumberOfSoldProducts(Date date, Boolean isAvailable);

    /**
     * Retrieves a list of the top-selling products, limited to a specified number of products.
     * If the number of products is not specified, a default of 5 products is returned.
     *
     * @param numOfProducts the maximum number of top-selling products to retrieve. 
     * If null, the default is 5.
     * @return a list of top-selling products, sorted by number of subscribers.
     */
    public List<Product> getTopProducts(Integer numOfProducts);

}