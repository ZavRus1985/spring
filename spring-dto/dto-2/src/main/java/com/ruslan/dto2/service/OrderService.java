package com.ruslan.dto2.service;

import com.ruslan.dto2.entity.manytomany.Order;
import com.ruslan.dto2.entity.manytomany.Product;
import com.ruslan.dto2.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<Order> getAllOrder() {
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

    @Transactional
    public void addOrder(Integer orderId, List<Product> products) {
        Order order = repository.findById(orderId).orElseThrow(() -> new NoSuchElementException("Order not found"));
        for (Product product : products) {
            productService.getProductById(product.getId()).addOrder(order);//check - "Product not found"
        }
        this.saveOrder(order);
    }

    @Transactional
    public void orderPrice(Integer orderId) {

    }

}
