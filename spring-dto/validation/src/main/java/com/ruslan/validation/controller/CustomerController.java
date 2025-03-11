package com.ruslan.validation.controller;

import com.ruslan.validation.dto.CustomerDto;
import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Integer customerId) {
        return customerService.findById(customerId);
    }

    //-------------------------------------


    @PostMapping
    public void saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
    }

    //------------------------------------

    @PutMapping("/{id}")
    public ObjAdditionResponse updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto, id);
    }

    //-------------------------------------

    @DeleteMapping("/{id}")
    public ObjAdditionResponse deleteCustomer(@PathVariable Integer id) {
        return customerService.deleteCustomer(id);
    }

    //-------------------------------------

    @PutMapping("/upBalance/{id}")
    public ObjAdditionResponse upBalance(@PathVariable Integer id, @RequestBody BigDecimal amount) {
         return customerService.upBalance(id, amount);
    }

    //-------------------------------------

    @PutMapping("/withdrawMoney/{id}")
    public ObjAdditionResponse withdrawMoney(@PathVariable Integer id, @RequestBody BigDecimal amount) {
        return customerService.withdrawMoney(id, amount);
    }

    //-------------------------------------

    @GetMapping("/getBalance/{id}")
    public BigDecimal getBalance(@PathVariable Integer id) {
        return customerService.getBalance(id);
    }
}
