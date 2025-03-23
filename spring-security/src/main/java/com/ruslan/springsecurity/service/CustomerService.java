package com.ruslan.springsecurity.service;


import com.ruslan.springsecurity.dto.BankCardDto;
import com.ruslan.springsecurity.dto.CustomerDto;
import com.ruslan.springsecurity.dto.ObjAdditionResponse;
import com.ruslan.springsecurity.entity.BankCard;
import com.ruslan.springsecurity.entity.Customer;
import com.ruslan.springsecurity.repository.CustomerRepository;
import com.ruslan.springsecurity.service.mapper.CustomerMapper;
import com.ruslan.springsecurity.service.mapper.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public CustomerDto findById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Customer not found"));
        return customerMapper.toDto(customer);
    }


    @Transactional
    public void saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        BankCard bankCard = customer.getBankCard();
        if (bankCard != null) {
            customer.setBankCard(bankCard);
        }
        customerRepository.save(customer);
    }

        @Transactional
    public ObjAdditionResponse updateCustomer(CustomerDto customerDto, Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Customer not found"));

        customerMapper.update(customer, customerDto);
        customerRepository.save(customer);

        return new ObjAdditionResponse(true, "Customer updated successfully");
    }

    @Transactional
    public ObjAdditionResponse deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new NoSuchEntityException("Customer with id=%d not found".formatted(id));
        }
        customerRepository.deleteById(id);
        return new ObjAdditionResponse(true, "Customer deleted successfully");
    }

    /*
    Написать логику приложения: добавить три операции для клиента –
       * пополнить баланс,
       * вывести средства,
       * просмотреть баланс.
    Для выполнения данных операций необходимо все данные передавать в теле запроса.
     */

    @Transactional
    public ObjAdditionResponse upBalance(Integer customerId, BigDecimal amount){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchEntityException("Customer not found"));

        CustomerDto customerDto = customerMapper.toDto(customer);
        BankCardDto bankCardDto = customerDto.getBankCard();
        bankCardDto.setBalance(bankCardDto.getBalance().add(amount));
        customer.getBankCard().setBalance(bankCardDto.getBalance());

        this.updateCustomer(customerDto, customerId);

        return new ObjAdditionResponse(true, "The customer card balance has been updated successfully.");
    }

    @Transactional
    public ObjAdditionResponse withdrawMoney(Integer customerId, BigDecimal amount){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchEntityException("Customer not found"));

        CustomerDto customerDto = customerMapper.toDto(customer);
        BankCardDto bankCardDto = customerDto.getBankCard();
        if(bankCardDto.getBalance().compareTo(amount) > 0){
            customerDto.getBankCard().setBalance(bankCardDto.getBalance().subtract(amount));
            this.updateCustomer(customerDto, customerId);
        }
        else
            return new ObjAdditionResponse(true, "There are not enough balance on the card.");

        return new ObjAdditionResponse(true, "The customer card balance has been updated successfully.");
    }

    @Transactional
    public BigDecimal getBalance(Integer customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchEntityException("Customer not found"));

        return customer.getBankCard().getBalance();
    }
}
