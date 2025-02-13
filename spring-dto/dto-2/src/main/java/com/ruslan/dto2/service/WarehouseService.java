package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.onetomany.Warehouse;
import com.ruslan.dto2.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final Warehouse warehouse = new Warehouse();

    @Transactional(readOnly = true)
    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public void saveWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public Warehouse findById(int i) {
       return warehouseRepository.findById(i).orElse(warehouse);
    }
}
