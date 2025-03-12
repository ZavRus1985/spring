package com.ruslan.validation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_card_id", referencedColumnName = "id")
    private BankCard bankCard;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setOrderItems(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setCustomer(this);
        }
        this.orderItems = orderItems;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }
}
