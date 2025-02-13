package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.onetomany.Stock;
import com.ruslan.dto2.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public List<Stock> getProductItems() {
        return stockService.getAllStocks();
    }

    @PostMapping
    public void saveProductItem(@RequestBody Stock stock) {
        stockService.saveStock(stock);
    }

    @PutMapping("/increaseCount")
    public void addCountProduct(@RequestBody Stock stock) {
        stockService.increaseProduct(stock);
    }

    @PutMapping("/decreaseCount")
    public void deleteCountProduct(@RequestBody Stock stock) {
        stockService.decreaseProduct(stock);
    }
}
