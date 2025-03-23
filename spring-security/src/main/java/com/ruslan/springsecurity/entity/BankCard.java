package com.ruslan.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bank_cards")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bankName;

    private Integer numberCard;

    private Integer cvcCode;

    private BigDecimal balance;

    @OneToOne(mappedBy = "bankCard", cascade = CascadeType.ALL)
    private Customer customer;

    public BankCard(String bankName, Integer numberCard, Integer cvcCode, BigDecimal balance) {
        this.bankName = bankName;
        this.numberCard = numberCard;
        this.cvcCode = cvcCode;
        this.balance = balance;
    }

    public BankCard() {

    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.setBankCard(this);
    }
}
