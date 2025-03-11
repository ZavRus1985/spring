package com.ruslan.dto2.service;

import com.ruslan.dto2.dto.ObjectAdditionalResponse;
import com.ruslan.dto2.dto.ProductAdditionRequest;
import com.ruslan.dto2.entity.onetomany.Product;
import com.ruslan.dto2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //---------------------------------------------------------------

    @Transactional(readOnly = true)
    public List<ProductAdditionRequest> getAllProducts() {
        List<ProductAdditionRequest> products = productRepository.findAll().stream().
                map(p -> new ProductAdditionRequest(p.getCategory(), p.getName(), p.getPrice())).toList();
        return products;
    }

    @Transactional(readOnly = true)
    public ProductAdditionRequest findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return new ProductAdditionRequest(product.get().getCategory(), product.get().getName(), product.get().getPrice());
        }
       throw new NoSuchElementException("Product not found");
    }

    @Transactional
    public List<ProductAdditionRequest> findByCategory(String category) {
        List<ProductAdditionRequest> products = productRepository.findAll().stream().
                map(p -> new ProductAdditionRequest(p.getCategory(), p.getName(), p.getPrice())).
                filter(p -> p.getCategory().equals(category)).
                toList();
        return products;
    }

    @Transactional
    public List<ProductAdditionRequest> findByPriceBetween(Double lowerPrice, Double higherPrice ) {
        List<ProductAdditionRequest> products = productRepository.findAll().stream().
                map(p -> new ProductAdditionRequest(p.getCategory(), p.getName(), p.getPrice())).
                filter(p -> p.getPrice().compareTo(new BigDecimal(lowerPrice)) >= 0
                        && p.getPrice().compareTo(new BigDecimal(higherPrice)) <= 0).
                toList();
        return products;
    }

    //---------------------------------------------------------------

    @Transactional
    public ObjectAdditionalResponse saveProduct(ProductAdditionRequest product) {
        Product saveProduct = new Product();
        saveProduct.setName(product.getName());
        saveProduct.setCategory(product.getCategory());
        saveProduct.setPrice(product.getPrice());

        productRepository.save(saveProduct);

        return new ObjectAdditionalResponse(true, "Product saved successfully");
    }
    //---------------------------------------------------------------

    @Transactional
    public ObjectAdditionalResponse updateProduct(Integer id, ProductAdditionRequest product) {
        Product updatebableProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        updatebableProduct.setName(product.getName());
        updatebableProduct.setCategory(product.getCategory());
        updatebableProduct.setPrice(product.getPrice());

        productRepository.updateProduct(updatebableProduct.getName(),
                updatebableProduct.getCategory(),
                String.valueOf(updatebableProduct.getPrice()),
                id);
        return new ObjectAdditionalResponse(true, "Product updated successfully");
    }
    //---------------------------------------------------------------

    @Transactional
    public ObjectAdditionalResponse deleteProduct(Integer id) {
        int deleted = productRepository.deleteProductById(id);
        if (deleted == 0) {
            throw new NoSuchElementException("Product not found");
        }
        return new ObjectAdditionalResponse(true, "Product deleted successfully");
    }
}
