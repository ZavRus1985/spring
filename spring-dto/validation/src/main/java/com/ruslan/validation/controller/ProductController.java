package com.ruslan.validation.controller;

import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.dto.ProductAdditionRequest;
import com.ruslan.validation.service.ProductService;
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

    //-------------------------------------

    @PostMapping
    public ObjAdditionResponse saveProduct(@RequestBody ProductAdditionRequest product) {
        return productService.saveProduct(product);
    }

    //------------------------------------

    @PutMapping("/{id}")
    public ObjAdditionResponse updateProduct(@PathVariable Integer id, @RequestBody ProductAdditionRequest product) {
        return productService.updateProduct(id, product);
    }

    //------------------------------------

    @DeleteMapping("/{id}")
    public ObjAdditionResponse deleteProduct(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }

    //------------------------------------

}