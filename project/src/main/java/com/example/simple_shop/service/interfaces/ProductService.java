package com.example.simple_shop.service.interfaces;

import java.util.List;

import com.example.simple_shop.entity.Product;

public interface ProductService {
 
    public Product saveProduct(Product product);

    public Product getProductByID(Long productID);

    public List<Product> getProductsBySubscriberID(Long subsctriberID);

    public Product updateProduct(Long productID, Product newProductData);

    public void deleteProductByID(Long productID);

}