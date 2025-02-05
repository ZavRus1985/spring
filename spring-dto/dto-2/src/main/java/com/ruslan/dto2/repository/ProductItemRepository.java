package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

    ProductItem findByProductId(int productId);
}
