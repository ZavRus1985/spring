package com.ruslan.dto2.controller;


import com.ruslan.dto2.dto.ObjectAdditionalResponse;
import com.ruslan.dto2.dto.ProductAdditionRequest;
import com.ruslan.dto2.entity.onetomany.Product;
import com.ruslan.dto2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductAdditionRequest> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductAdditionRequest getProductById(@PathVariable Integer productId) {
        return productService.findById(productId);
    }

    @GetMapping("/field") // http://localhost:8090/api/products/field?category=IT
    public List<ProductAdditionRequest> getProductByCategory(@RequestParam String category) {
        return productService.findByCategory(category);
    }

    @GetMapping("/price") //  http://localhost:8090/api/products/price?lowerPrice=15&higherPrice=40
    public List<ProductAdditionRequest> findByPriceBetween(@RequestParam Double lowerPrice, @RequestParam Double higherPrice) {
        return productService.findByPriceBetween(lowerPrice, higherPrice);
    }

    //-------------------------------------

    @PostMapping
    public ObjectAdditionalResponse saveProduct(@RequestBody ProductAdditionRequest product) {
        return productService.saveProduct(product);
    }

    //------------------------------------

    @PutMapping("/{id}")
    public ObjectAdditionalResponse updateProduct(@PathVariable Integer id, @RequestBody ProductAdditionRequest product) {
        return productService.updateProduct(id, product);
    }

   //------------------------------------

    @DeleteMapping("/{id}")
    public ObjectAdditionalResponse deleteProduct(@PathVariable Integer id) {
        return deleteProduct(id);
    }

    //------------------------------------

}
