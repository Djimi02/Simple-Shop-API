package com.example.simple_shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.simple_shop.entity.Product;
import com.example.simple_shop.entity.Subscriber;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT s FROM Subscriber s JOIN s.subscribedProducts p WHERE p.id = :productID")
    List<Subscriber> getSubsribersByProductID(@Param("productID") Long productID);

    @Modifying
    @Query(
        nativeQuery = true,
        value = "DELETE FROM subscriber_product WHERE product_id = :productID"
    )
    public void deleteAllReferencesInTheMappingTable(@Param("productID") Long productID);

    @Query("SELECT p FROM Product p JOIN p.subscibers s GROUP BY p.id ORDER BY COUNT(s.id) DESC")
    public List<Product> getTopProducts(Pageable pageable);

    @Query(
        nativeQuery = true,
        value = "SELECT COUNT(*) FROM product " +
                "WHERE (DATE(creation_date) = :date OR cast(:date as date) is null) AND " +
                "(is_available = :isAvailable OR cast(:isAvailable as boolean) is null)"
    )
    public Long getNumberOfProductsByDateAndAvailability(@Param("date") Date date, @Param("isAvailable") Boolean isAvailable);

}