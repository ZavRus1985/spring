package com.ruslan.dto2.controller;


import com.ruslan.dto2.entity.manytomany.Product;
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
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    //-------------------------------------

    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    //------------------------------------

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

   //------------------------------------

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    //------------------------------------

    @GetMapping("/category")
    public List<Product> getProductByCategory(@RequestParam String category) {
        return productService.findByCategory(category);
    }

    @GetMapping("/price") //  http://localhost:8090/api/products/price?lowerPrice=15&higherPrice=40
    public List<Product> findByPriceBetween(@RequestParam Double lowerPrice, @RequestParam Double higherPrice) {
        return productService.findByPriceBetween(lowerPrice, higherPrice);
    }
}
