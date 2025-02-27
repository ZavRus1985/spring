package com.ruslan.dto2.controller;

import com.ruslan.dto2.dto.StockDto;
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
    public void saveProductItem(@RequestBody StockDto stock) {
        stockService.saveStock(stock);
    }

    @PutMapping("/increase-count")
    public void addCountProduct(@RequestBody StockDto stockDto) {
        stockService.increaseProduct(stockDto);
    }

    @PutMapping("/decrease-dount")
    public void deleteCountProduct(@RequestBody StockDto stockDto) {
        stockService.decreaseProduct(stockDto);
    }
}
