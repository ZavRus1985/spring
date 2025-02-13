package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.onetomany.Order;
import com.ruslan.dto2.entity.onetomany.Product;
import com.ruslan.dto2.entity.onetomany.OrderItem;
import com.ruslan.dto2.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @PostMapping
    public void saveOrderItem(@RequestBody OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
    }

    @PutMapping("/{orderItemId}")
    public void addOrderItem(@PathVariable Integer orderItemId, @RequestBody OrderItem orderItem) {
        orderItemService.addOrderItem(orderItemId, orderItem);
    }
}
