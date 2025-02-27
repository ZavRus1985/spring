package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByProductIdAndWarehouseId(int productId, int warehouseId);

}
