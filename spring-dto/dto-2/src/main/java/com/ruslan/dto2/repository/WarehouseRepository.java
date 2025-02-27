package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Optional<Warehouse> findById(Integer id);
}
