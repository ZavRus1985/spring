package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findByProductId(int productId);
}
