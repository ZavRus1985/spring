package com.ruslan.springsecurity.repository;


import com.ruslan.springsecurity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

}
