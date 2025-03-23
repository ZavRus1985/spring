package com.ruslan.springsecurity.repository;



import com.ruslan.springsecurity.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

        @Query("select o from Order o left join fetch o.orderItems where o.id = :orderId")
        Optional<Order> findById(Integer orderId);



}
