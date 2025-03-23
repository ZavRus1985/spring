package com.ruslan.springsecurity.controller;



import com.ruslan.springsecurity.entity.Order;
import com.ruslan.springsecurity.entity.OrderItem;
import com.ruslan.springsecurity.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public List<Order> getOrder() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @GetMapping("/{orderId}")
    public void getOrderPrice(@PathVariable int orderId) {
        orderService.orderPrice(orderId);
    }

    @GetMapping("/status/{orderId}")  // http://localhost:8090/api/orders/status/1
    public void orderStatus(@PathVariable int orderId) {
        orderService.confirmOrder(orderId);
    }

    /*
                        ORDER_ITEM CONTROLLER

    */

    @GetMapping("/orderItems")
    public List<OrderItem> getAllOrderItems() {
        return orderService.getAllOrderItems();
    }

    @PostMapping("/orderItems")
    public void saveOrderItem(@RequestBody OrderItem orderItem) {
        orderService.saveOrderItem(orderItem);
    }

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
    }
}
