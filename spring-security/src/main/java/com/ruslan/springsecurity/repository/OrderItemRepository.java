package com.ruslan.springsecurity.repository;



import com.ruslan.springsecurity.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
