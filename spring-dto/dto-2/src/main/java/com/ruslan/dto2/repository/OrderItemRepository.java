package com.ruslan.dto2.repository;

import com.ruslan.dto2.entity.onetomany.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
