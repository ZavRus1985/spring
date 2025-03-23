package com.ruslan.springsecurity.service.mapper;


import com.ruslan.springsecurity.dto.ProductAdditionRequest;
import com.ruslan.springsecurity.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductAdditionRequest productAdditionRequest);

    ProductAdditionRequest toProductAdditionRequest(Product product);

    void update(@MappingTarget Product product, ProductAdditionRequest productAdditionRequest);
}
