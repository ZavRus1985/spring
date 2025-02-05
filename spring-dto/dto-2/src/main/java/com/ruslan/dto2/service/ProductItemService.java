package com.ruslan.dto2.service;


import com.ruslan.dto2.entity.manytomany.Product;
import com.ruslan.dto2.entity.onetomany.ProductItem;
import com.ruslan.dto2.repository.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductItemService {

    private final ProductService productService;
    private final ProductItemRepository productItemRepository;

    @Transactional
    public List<ProductItem> getAllProductItems() {
        return productItemRepository.findAll();
    }

    @Transactional
    public void saveProductItem(ProductItem productItem) {

        ProductItem savedProductItem = new ProductItem();
        Product product = productService.getProductById(productItem.getProduct().getId());

        savedProductItem.setProduct(product);
        savedProductItem.setCount(productItem.getCount());
        savedProductItem.setWarehouse(product.getWarehouse());

        productItemRepository.save(savedProductItem);
    }

    @Transactional
    public void addCountProduct(ProductItem productItem) {
        ProductItem productItemByProductId = productItemRepository.
                findByProductId(productItem.getProduct().getId());
        if (productItemByProductId != null) {
            productItemByProductId.setCount(productItem.getCount() + productItemByProductId.getCount());
        }
        productItemRepository.save(productItemByProductId);
    }

    @Transactional
    public void deleteCountProduct(ProductItem productItem) {
        ProductItem productItemByProductId = productItemRepository.
                findByProductId(productItem.getProduct().getId());
        if (productItemByProductId != null) {
            productItemByProductId.setCount(productItemByProductId.getCount() - productItem.getCount());
        }
        productItemRepository.save(productItemByProductId);
    }



}
