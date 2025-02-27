package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.onetomany.Warehouse;
import com.ruslan.dto2.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public Optional<Warehouse> findById(int id) {
        Warehouse warehouse = warehouseRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        return Optional.ofNullable(warehouse);
    }

    @Transactional(readOnly = true)
    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public void saveWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }


}
