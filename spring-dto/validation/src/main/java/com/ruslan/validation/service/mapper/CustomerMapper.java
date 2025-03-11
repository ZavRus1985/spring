package com.ruslan.validation.service.mapper;


import com.ruslan.validation.dto.CustomerDto;
import com.ruslan.validation.entity.BankCard;
import com.ruslan.validation.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = BankCard.class)
public interface CustomerMapper{

    @Mapping(target = "bankCard",
            expression = "java(new BankCard(" +
                                    "  customerDto.getBankName()" +
                                    ", customerDto.getNumberCard()" +
                                    ", customerDto.getCvcCode()" +
                                    ", customerDto.getBalance() ))")
    Customer toEntity(CustomerDto customerDto);


    @Mapping(target = "bankName", source = "bankCard.bankName")
    @Mapping(target = "numberCard", source = "bankCard.numberCard")
    @Mapping(target = "cvcCode", source = "bankCard.cvcCode")
    @Mapping(target = "balance", source = "bankCard.balance")
    CustomerDto toDto(Customer customer);

    void update(@MappingTarget Customer customer, CustomerDto dto);
}
