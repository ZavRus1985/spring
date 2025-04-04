package com.ruslan.validation.service;


import com.ruslan.validation.entity.Customer;
import com.ruslan.validation.entity.Order;
import com.ruslan.validation.entity.OrderItem;
import com.ruslan.validation.entity.Product;
import com.ruslan.validation.model.OrderStatus;
import com.ruslan.validation.repository.CustomerRepository;
import com.ruslan.validation.repository.OrderItemRepository;
import com.ruslan.validation.repository.OrderRepository;
import com.ruslan.validation.repository.ProductRepository;
import com.ruslan.validation.service.mapper.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


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
        * добавления нового заказа
 */

    @Transactional()
    public void createOrder(Order order) {

        Order newOrder = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new NoSuchEntityException("Product not found with ID: " + orderItem.getProduct().getId()));

            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }

        newOrder.setOrderItems(orderItemList);
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new NoSuchEntityException("Customer not found"));
        newOrder.setCustomer(customer);
        orderRepository.save(newOrder);

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

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchEntityException("Order not found with ID: " + orderId));

        List<OrderItem> orderItems = order.getOrderItems();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            BigDecimal itemPrice = item.getProduct().getPrice();
            totalPrice = totalPrice.add(itemPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        order.setPrice(totalPrice);
        orderRepository.save(order);
    }

    @Transactional()
    public void confirmOrder(Integer orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchEntityException("Order not found with ID: " + orderId));

        orderPrice(orderId);

        BigDecimal orderPrice = order.getPrice();
        Customer customer = order.getCustomer();

        BigDecimal currentBalance = customer.getBankCard().getBalance();
        if (currentBalance.compareTo(orderPrice) < 0) {
            throw new IllegalArgumentException("Insufficient balance to confirm the order.");
        }

        // Списываем средства
        customer.getBankCard().setBalance(currentBalance.subtract(orderPrice));

        order.setOrderStatus(OrderStatus.CONFIRMED);
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

}
