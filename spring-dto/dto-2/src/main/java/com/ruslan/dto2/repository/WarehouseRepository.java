package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {


}
