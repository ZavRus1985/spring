package com.ruslan.dto2.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruslan.dto2.entity.manytomany.Product;
import com.ruslan.dto2.entity.onetomany.Warehouse;
import com.ruslan.dto2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final WarehouseService warehouseService;


    @Transactional(readOnly = true)
    public List<Product> getAllProduct() {
        return productRepository.findAllProducts();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        return product;
    }

    @Transactional
    public void saveProduct(Product product) {
        Product saveProduct = new Product();
        saveProduct.setName(product.getName());
        saveProduct.setCategory(product.getCategory());
        saveProduct.setPrice(product.getPrice());

        Warehouse warehouse = warehouseService.findById(1);
        saveProduct.setWarehouse(warehouse);

        productRepository.save(saveProduct);
    }


    @Transactional
    public void updateProduct(Integer id, Product product) {
        Product updatebableProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        updatebableProduct.setName(product.getName());
        updatebableProduct.setPrice(product.getPrice());
        updatebableProduct.setCategory(product.getCategory());

        productRepository.updateProduct(updatebableProduct.getName(),
                String.valueOf(updatebableProduct.getPrice()),
                updatebableProduct.getCategory()
                ,id);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        int deleted = productRepository.deleteProductById(id);
        if (deleted == 0) {
            throw new NoSuchElementException("Product not found");
        }
    }

    @Transactional
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Transactional
    public List<Product> findByPriceBetween(Double lowerPrice, Double higherPrice ) {
        return productRepository.findByPriceBetween(lowerPrice, higherPrice);
    }
}
