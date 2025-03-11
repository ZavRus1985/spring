package com.ruslan.validation.repository;

import com.ruslan.validation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

}
