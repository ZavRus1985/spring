package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Product SET name = :name, category = :category, price = :price where id = :id")
    int updateProduct(String name, String category, String price, Integer id);

    @Modifying
    @Transactional
    @Query("delete from Product p where p.id = :id")
    int deleteProductById(Integer id);

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double lower, Double higher);
}
