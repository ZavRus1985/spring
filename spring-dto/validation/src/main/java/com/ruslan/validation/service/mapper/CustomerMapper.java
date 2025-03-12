package com.ruslan.validation.service.mapper;


import com.ruslan.validation.dto.CustomerDto;
import com.ruslan.validation.entity.BankCard;
import com.ruslan.validation.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = BankCard.class)
public interface CustomerMapper{


    Customer toEntity(CustomerDto customerDto);


    CustomerDto toDto(Customer customer);

    void update(@MappingTarget Customer customer, CustomerDto dto);
}
