package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.manytomany.Order;
import com.ruslan.dto2.entity.manytomany.Product;
import com.ruslan.dto2.entity.onetomany.OrderItem;
import com.ruslan.dto2.repository.OrderItemRepository;
import com.ruslan.dto2.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Transactional
    public void addOrderItem(Integer orderItemId, Product product, Order order) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(()
                -> new NoSuchElementException("OrderItem not found"));

        Order orderFindedDB = orderService.getOrderById(order.getId());
        orderItem.setOrder(orderFindedDB);

        Product productFindedDB = productService.getProductById(product.getId());
        orderItem.setProduct(productFindedDB);
    }
}
