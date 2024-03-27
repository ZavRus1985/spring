package org.ruslan.hib.entity.manytomany;


import jakarta.persistence.*;
import org.ruslan.hib.entity.onetomany.Producer;
import org.ruslan.hib.entity.onetoone.Customer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    Customer customer;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Product> products = new ArrayList<>();

    public Order() {
    }

    public Order(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        for (Product product: products) {
            product.addOrder(this);
        }
        this.products = products;
    }
}
