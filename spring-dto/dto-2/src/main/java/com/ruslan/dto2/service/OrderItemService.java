package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.onetomany.Order;
import com.ruslan.dto2.entity.onetomany.Product;
import com.ruslan.dto2.entity.onetomany.OrderItem;
import com.ruslan.dto2.repository.OrderItemRepository;
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

 /*
### part2.
1.	Добавить сущность Заказ. Заказ состоит из товаров.
2.	Добавить API для
        * добавления нового заказа
 */

    @Transactional()
    public void addOrderItem(Integer orderItemId, OrderItem orderItem) {

        OrderItem orderItemFindedDB = orderItemRepository.findById(orderItemId).orElseThrow(()
                -> new NoSuchElementException("OrderItem not found"));

        orderItemFindedDB.setQuantity(orderItem.getQuantity());
        Order orderFindedDB = orderService.getOrderById(orderItem.getOrder().getId());
        orderItemFindedDB.setOrder(orderFindedDB);
        orderFindedDB.setOrderItems(orderFindedDB.getOrderItems());

        Product productFindedDB = productService.getProductById(orderItem.getProduct().getId());
        orderItemFindedDB.setProduct(productFindedDB);
        orderItemRepository.save(orderItemFindedDB);
    }
}
