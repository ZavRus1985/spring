package com.ruslan.validation.service;


import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.dto.ProductAdditionRequest;
import com.ruslan.validation.entity.Product;
import com.ruslan.validation.repository.ProductRepository;
import com.ruslan.validation.service.mapper.ProductMapper;
import com.ruslan.validation.service.mapper.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    //---------------------------------------------------------------

    @Transactional(readOnly = true)
    public List<ProductAdditionRequest> getAllProducts() {
        List<ProductAdditionRequest> products = productRepository.findAll().stream()
                .map(productMapper::toProductAdditionRequest)
                .toList();
        return products;
    }

    @Transactional(readOnly = true)
    public ProductAdditionRequest findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return productMapper.toProductAdditionRequest(product.get());
        }
        throw new NoSuchEntityException("Product not found");
    }

    //---------------------------------------------------------------

    @Transactional
    public ObjAdditionResponse saveProduct(ProductAdditionRequest product) {
        Product saveProduct = productMapper.toEntity(product);
        productRepository.save(saveProduct);
        return new ObjAdditionResponse(true, "Product saved successfully");
    }
    //---------------------------------------------------------------

    @Transactional
    public ObjAdditionResponse updateProduct(Integer id, ProductAdditionRequest product) {
        Product updatebableProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Product not found"));

        updatebableProduct.setName(product.getName());
        updatebableProduct.setCategory(product.getCategory());
        updatebableProduct.setPrice(product.getPrice());

        productRepository.updateProduct(updatebableProduct.getName(),
                updatebableProduct.getCategory(),
                String.valueOf(updatebableProduct.getPrice()),
                id);
        return new ObjAdditionResponse(true, "Product updated successfully");
    }
    //---------------------------------------------------------------

    @Transactional
    public ObjAdditionResponse deleteProductById(Integer id) {
        int deleted = productRepository.deleteProductById(id);
        if (deleted == 0) {
            throw new NoSuchEntityException("Product not found");
        }
        return new ObjAdditionResponse(true, "Product deleted successfully");
    }

//    @Transactional
//    public void deleteAllProducts() {
//        List<ProductAdditionRequest> products = productRepository.findAll().stream()
//                .map(productMapper::toProductAdditionRequest)
//                .toList();
//        for (ProductAdditionRequest product : products) {
//            product
//        }
//
//    }
}
