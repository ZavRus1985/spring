package com.ruslan.dto2.service;

import com.ruslan.dto2.dto.OrderItemDto;
import com.ruslan.dto2.entity.onetomany.Order;
import com.ruslan.dto2.entity.onetomany.OrderItem;
import com.ruslan.dto2.model.OrderStatus;
import com.ruslan.dto2.repository.OrderItemRepository;
import com.ruslan.dto2.repository.OrderRepository;
import com.ruslan.dto2.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        return order;
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
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

        Order orderFindedDB = orderRepository.findById(orderId).orElseThrow(()
                -> new NoSuchElementException("Order not found"));
        List<OrderItem> orderItem = orderFindedDB.getOrderItems();

        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO,  2);
        BigDecimal amount = new BigDecimal(BigInteger.ZERO,  2);

        for (OrderItem orderItemDB : orderItem) {
            totalPrice = orderItemDB.getProduct().getPrice().multiply(new BigDecimal(orderItemDB.getQuantity()));
            amount = amount.add(totalPrice);
        }
        orderFindedDB.setPrice(amount);
        orderRepository.save(orderFindedDB);
    }

    @Transactional()
    public void confirmOrder(Integer orderId) {
        Order orderFindedDB = orderRepository.findById(orderId).orElseThrow(()
                -> new NoSuchElementException("Order not found"));
        orderFindedDB.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepository.save(orderFindedDB);
    }

/*
                        ORDER_ITEM SERVICE

 */

    @Transactional(readOnly = true)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    /*
### part2.
1.	Добавить сущность Заказ. Заказ состоит из товаров.
2.	Добавить API для
        * добавления нового заказа
 */

    @Transactional()
    public void createOrder(List<OrderItemDto> orderItems) {

        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemDto orderItemDTO : orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setProduct(productRepository.findById(orderItemDTO.getProductId()).get());
            orderItemList.add(orderItem);
        }
        order.setOrderItems(orderItemList);
        orderRepository.save(order);
    }
}
