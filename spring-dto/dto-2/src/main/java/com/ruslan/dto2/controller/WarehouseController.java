package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.onetomany.Warehouse;
import com.ruslan.dto2.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getWarehouse() {
        return warehouseService.getAllWarehouse();
    }

    @PostMapping
    public void saveWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.saveWarehouse(warehouse);
    }
}
