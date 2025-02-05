package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.manytomany.Order;
import com.ruslan.dto2.entity.manytomany.Product;
import com.ruslan.dto2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/order")
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @PostMapping("/save")
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @PostMapping("/add/{orderId}")
    public void addOrders(@PathVariable Integer orderId, @RequestBody List<Product> products) {
        orderService.addOrder(orderId, products);
    }


}
