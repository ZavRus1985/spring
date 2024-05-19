package org.ruslan.hib.entity.manytomany;

import jakarta.persistence.*;
import org.ruslan.hib.entity.onetomany.Category;
import org.ruslan.hib.entity.onetomany.Producer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producer_id", referencedColumnName = "producer_id")
    Producer producer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    Category category;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "products_orders",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "order_id"))
    List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Product(Integer id, String name) {
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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
