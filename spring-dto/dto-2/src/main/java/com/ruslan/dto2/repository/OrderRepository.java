package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.manytomany.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {


}
