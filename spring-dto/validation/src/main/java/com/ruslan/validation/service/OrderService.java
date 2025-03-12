package com.ruslan.validation.service;


import com.ruslan.validation.dto.OrderItemDto;
import com.ruslan.validation.entity.Order;
import com.ruslan.validation.entity.OrderItem;
import com.ruslan.validation.model.OrderStatus;
import com.ruslan.validation.repository.CustomerRepository;
import com.ruslan.validation.repository.OrderItemRepository;
import com.ruslan.validation.repository.OrderRepository;
import com.ruslan.validation.service.mapper.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;


    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Order not found"));
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

        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new NoSuchEntityException("Order not found"));
        List<OrderItem> orderItems = order.getOrderItems();

        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO,  2);
        BigDecimal amount = new BigDecimal(BigInteger.ZERO,  2);

        for (OrderItem orderItem : orderItems) {
            totalPrice = orderItem.getCustomer().getBankCard().getBalance().multiply(new BigDecimal(orderItem.getQuantity()));
            amount = amount.add(totalPrice);
        }
        order.setPrice(amount);
        orderRepository.save(order);
    }

    @Transactional()
    public void confirmOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new NoSuchEntityException("Order not found"));
        order.setOrderStatus(OrderStatus.CONFIRMED);

        orderPrice(orderId);

        orderRepository.save(order);
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
            orderItem.setCustomer(customerRepository.findById(orderItemDTO.getCustomerId()).get());
            orderItemList.add(orderItem);
        }
        order.setOrderItems(orderItemList);
        orderRepository.save(order);
    }
}
