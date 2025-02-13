package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.onetomany.Product;
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

    //---------------------------------------------------------------

    @Transactional(readOnly = true)
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        return product;
    }

    @Transactional
    public List<Product> findByCategory(String category) {
        System.out.println(category);
        return productRepository.findByCategory(category);
    }

    @Transactional
    public List<Product> findByPriceBetween(Double lowerPrice, Double higherPrice ) {
        return productRepository.findByPriceBetween(lowerPrice, higherPrice);
    }

    //---------------------------------------------------------------

    @Transactional
    public void saveProduct(Product product) {
        Product saveProduct = new Product();
        saveProduct.setName(product.getName());
        saveProduct.setCategory(product.getCategory());
        saveProduct.setPrice(product.getPrice());

        productRepository.save(saveProduct);
    }
    //---------------------------------------------------------------

    @Transactional
    public void updateProduct(Integer id, Product product) {
        Product updatebableProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        updatebableProduct.setName(product.getName());
        updatebableProduct.setCategory(product.getCategory());
        updatebableProduct.setPrice(product.getPrice());

        productRepository.updateProduct(updatebableProduct.getName(),
                updatebableProduct.getCategory(),
                String.valueOf(updatebableProduct.getPrice()),
                id);
    }
    //---------------------------------------------------------------

    @Transactional
    public void deleteProduct(Integer id) {
        int deleted = productRepository.deleteProductById(id);
        if (deleted == 0) {
            throw new NoSuchElementException("Product not found");
        }
    }

}
