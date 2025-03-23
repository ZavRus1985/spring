package com.ruslan.springsecurity.service.mapper;


import com.ruslan.springsecurity.dto.CustomerDto;
import com.ruslan.springsecurity.entity.BankCard;
import com.ruslan.springsecurity.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = BankCard.class)
public interface CustomerMapper{


    Customer toEntity(CustomerDto customerDto);


    CustomerDto toDto(Customer customer);

    void update(@MappingTarget Customer customer, CustomerDto dto);
}
