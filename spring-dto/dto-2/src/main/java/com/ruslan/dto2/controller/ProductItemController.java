package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.onetomany.ProductItem;
import com.ruslan.dto2.service.ProductItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/productitem")
public class ProductItemController {

    private final ProductItemService productItemService;

    @GetMapping
    public List<ProductItem> getProductItems() {
        return productItemService.getAllProductItems();
    }

    @PostMapping("/save")
    public void saveProductItem(@RequestBody ProductItem productItem) {
        productItemService.saveProductItem(productItem);
    }

    @PutMapping("/add")
    public void addCountProduct(@RequestBody ProductItem productItem) {
        productItemService.addCountProduct(productItem);
    }

    @PutMapping("/delete")
    public void deleteCountProduct(@RequestBody ProductItem productItem) {
        productItemService.deleteCountProduct(productItem);
    }
}
