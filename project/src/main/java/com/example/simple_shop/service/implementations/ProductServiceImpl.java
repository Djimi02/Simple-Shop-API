package com.example.simple_shop.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.repository.ProductRepository;
import com.example.simple_shop.service.interfaces.ProductService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository prodRepo;
    
    @Override
    public Product saveProduct(Product product) {
        checkProductFields(product);

        // Assume initial product cannot have subscribers
        product.setSubscibers(null);
        // Set current date
        product.setCreationDate(new Date());
        return prodRepo.save(product);
    }

    @Override
    public Product getProductByID(Long productID) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        return prodRepo.findById(productID).orElseThrow(
            () -> new IllegalArgumentException(String.format("Product with id = %2d does not exist!", productID))
        );
    }

    @Override
    public List<Product> getProductsBySubscriberID(Long subsctriberID) {
        if (subsctriberID == null) {
            throw new IllegalArgumentException("Subscriber ID cannot be null");
        }

        return prodRepo.getProductsBySubscriberID(subsctriberID);
    }

    @Transactional
    @Override
    public Product updateProduct(Long productID, Product newProductData) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        checkProductFields(newProductData);

        Product dbProd = prodRepo.findById(productID).orElseThrow(
            () -> new IllegalArgumentException(String.format("Product with id = %2d does not exist!", productID))
        );

        // Update data in db
        dbProd.setName(newProductData.getName());
        return dbProd;
    }

    @Override
    public void deleteProductByID(Long productID) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        } else if (!prodRepo.existsById(productID)) {
            throw new IllegalArgumentException(String.format("Product with id = %2d does not exist!", productID));
        }
        prodRepo.deleteById(productID);
    }

    private void checkProductFields(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null!");
        } else if (product.getName() == null) {
            throw new IllegalArgumentException("Product name cannot be null!");
        }
    }

    @Override
    public boolean productExistsByID(Long productID) {
        if (productID == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }

        return prodRepo.existsById(productID);
    }
    
}