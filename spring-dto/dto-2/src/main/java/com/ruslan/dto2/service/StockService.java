package com.ruslan.dto2.service;


import com.ruslan.dto2.dto.ObjectAdditionalResponse;
import com.ruslan.dto2.dto.StockDto;
import com.ruslan.dto2.entity.onetomany.Stock;
import com.ruslan.dto2.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

//    @Transactional
//    public ObjectAdditionalResponse saveStock(StockDto stockDto) {
//        Stock savedStock = new Stock();
//
//        ProductAdditionRequest product = productService.findById(stockDto.getProductId());
//        Optional<Warehouse> warehouse = warehouseService.findById(stockDto.getWarehouseId());
//
//        savedStock.setProduct(product);
//        savedStock.setWarehouse(warehouse.get());
//        savedStock.setCount(stockDto.getCount());
//
//        stockRepository.save(savedStock);
//        return new ObjectAdditionalResponse(true, "Operation saveStock confirmed");
//    }

    @Transactional
    public ObjectAdditionalResponse increaseProduct(StockDto stockDto) {
        Optional<Stock> productFindedDB = stockRepository.
                findByProductIdAndWarehouseId(stockDto.getProductId(), stockDto.getWarehouseId());
        if (productFindedDB.isPresent()) {
            productFindedDB.get().setCount(stockDto.getCount() + productFindedDB.get().getCount());
        }
        stockRepository.save(productFindedDB.get());
        return new ObjectAdditionalResponse(true, "Operation increaseProduct confirmed");
    }

    @Transactional
    public ObjectAdditionalResponse decreaseProduct(StockDto stockDto) {
        Optional<Stock> productFindedDB = stockRepository.
                findByProductIdAndWarehouseId(stockDto.getProductId(), stockDto.getWarehouseId());

        if (productFindedDB.isPresent()) {
            int productItemQuantity = productFindedDB.get().getCount() - stockDto.getCount();
            if (productItemQuantity < 0) {
                productItemQuantity = 0;
            }
            productFindedDB.get().setCount(productItemQuantity);
        }
        stockRepository.save(productFindedDB.get());
        return new ObjectAdditionalResponse(true, "Operation decreaseProduct confirmed");
    }

}
