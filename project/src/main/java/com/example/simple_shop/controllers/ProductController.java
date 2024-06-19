package com.example.simple_shop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;
import com.example.simple_shop.service.interfaces.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping()
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{productID}")
    public Product getProductByID(@PathVariable("productID") Long productID) {
        return productService.getProductByID(productID);
    }

    @GetMapping("/{productID}/subscribers")
    public List<Subscriber> getSubsribersByProductID(@PathVariable("productID") Long productID) {
        return productService.getSubsribersByProductID(productID);
    }

    @PutMapping("/{productID}")
    public Product updateProduct(@PathVariable("productID") Long productID, @RequestBody Product newProductData) {
        return productService.updateProduct(productID, newProductData);
    }

    @DeleteMapping("/{productID}")
    public void deleteProductByID(@PathVariable("productID") Long productID) {
        productService.deleteProductByID(productID);
    }
    
}