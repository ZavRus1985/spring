package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.onetomany.Warehouse;
import com.ruslan.dto2.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getSingleWarehouse() {
        return warehouseService.getAllWarehouse();
    }

    //-------------------------------------

    @PostMapping("/save")
    public void saveWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.saveWarehouse(warehouse);
    }
}
