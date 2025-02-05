package org.ruslan.jpa.entity.onetoone;


import jakarta.persistence.*;
import org.ruslan.jpa.entity.manytomany.Order;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String phone;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private AccountCustomer accountCustomer;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountCustomer getAccountCustomer() {
        return accountCustomer;
    }

    public void setAccountCustomer(AccountCustomer accountCustomer) {
        this.accountCustomer = accountCustomer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        for (Order order: orders) {
            order.setCustomer(this);
        }
        this.orders = orders;
    }
}
