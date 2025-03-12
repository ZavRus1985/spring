package com.ruslan.validation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslan.validation.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private BigDecimal price;

    private OrderStatus orderStatus = OrderStatus.NOT_CONFIRMED;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setOrderItems(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(this);
        }
        this.orderItems = orderItems;
    }
}
