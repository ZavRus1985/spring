package com.ruslan.validation.service.mapper;

import com.ruslan.validation.dto.ProductAdditionRequest;
import com.ruslan.validation.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductAdditionRequest productAdditionRequest);

    ProductAdditionRequest toProductAdditionRequest(Product product);

    void update(@MappingTarget Product product, ProductAdditionRequest productAdditionRequest);
}
