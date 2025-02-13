package com.ruslan.dto2.service;


import com.ruslan.dto2.entity.onetomany.Product;
import com.ruslan.dto2.entity.onetomany.Stock;
import com.ruslan.dto2.entity.onetomany.Warehouse;
import com.ruslan.dto2.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductService productService;
    private final StockRepository stockRepository;
    private final WarehouseService warehouseService;

    @Transactional
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();

    }

    @Transactional
    public void saveStock(Stock stock) {
        Stock savedStock = new Stock();
        Product product = productService.getProductById(stock.getProduct().getId());
        Warehouse warehouse = warehouseService.findById(stock.getWarehouse().getId());

        savedStock.setProduct(product);
        savedStock.setWarehouse(warehouse);
        savedStock.setCount(stock.getCount());

        stockRepository.save(savedStock);
    }

    @Transactional
    public void increaseProduct(Stock stock) {
        Stock productItemByProductId = stockRepository.
                findByProductId(stock.getProduct().getId());
        if (productItemByProductId != null) {
            productItemByProductId.setCount(stock.getCount() + productItemByProductId.getCount());
        }
        stockRepository.save(productItemByProductId);
    }

    @Transactional
    public void decreaseProduct(Stock stock) {
        Stock productItemByProductId = stockRepository.
                findByProductId(stock.getProduct().getId());

        if (productItemByProductId != null) {
            int productItemQuantity = productItemByProductId.getCount() - stock.getCount();
            if (productItemQuantity < 0) {
                productItemQuantity = 0;
            }
            productItemByProductId.setCount(productItemQuantity);
        }
        stockRepository.save(productItemByProductId);
    }

}
