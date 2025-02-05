package com.ruslan.dto2.controller;

import com.ruslan.dto2.entity.manytomany.Order;
import com.ruslan.dto2.entity.manytomany.Product;
import com.ruslan.dto2.entity.onetomany.OrderItem;
import com.ruslan.dto2.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @PostMapping("/save")
    public void saveOrderItem(@RequestBody OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
    }

    @PostMapping("/add/{orderItemId}")
    public void addOrderItem(@PathVariable Integer orderItemId, @RequestBody Product product
                            ,@RequestBody Order order) {
        orderItemService.addOrderItem(orderItemId, product, order);
    }
}
