package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.onetomany.Order;
import com.ruslan.dto2.entity.onetomany.OrderItem;
import com.ruslan.dto2.entity.onetomany.Product;
import com.ruslan.dto2.model.OrderStatus;
import com.ruslan.dto2.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Integer id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        return order;
    }

    @Transactional
    public void saveOrder(Order order) {
        repository.save(order);
    }

    /*
### part2.
1.	Добавить сущность Заказ. Заказ состоит из товаров.
2.	Добавить API для
       * расчета стоимости заказа
       * для подтверждения заказа (статус заказа выставить в «Подтвержден»).
*/

    @Transactional()
    public void orderPrice(Integer orderId) {

        Order orderFindedDB = repository.findById(orderId).orElseThrow(()
                -> new NoSuchElementException("Order not found"));
        Set<OrderItem> orderItem = orderFindedDB.getOrderItems();

        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO,  2);
        BigDecimal amount = new BigDecimal(BigInteger.ZERO,  2);

        for (OrderItem orderItemDB : orderItem) {
            totalPrice = orderItemDB.getProduct().getPrice().multiply(new BigDecimal(orderItemDB.getQuantity()));
            amount = amount.add(totalPrice);
        }
        orderFindedDB.setPrice(amount);
        repository.save(orderFindedDB);
    }

    @Transactional()
    public void orderStatus(Integer orderId) {
        Order orderFindedDB = repository.findById(orderId).orElseThrow(()
                -> new NoSuchElementException("Order not found"));
        orderFindedDB.setOrderStatus(OrderStatus.CONFIRMED);
        repository.save(orderFindedDB);
    }
}
