package com.ruslan.dto2.entity.manytomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslan.dto2.entity.onetomany.ProductItem;
import com.ruslan.dto2.entity.onetomany.Warehouse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

//    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "products_orders",
            joinColumns = { @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id")} )
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        this.orders.add(order);
        order.getProducts().add(this);
    }
}
