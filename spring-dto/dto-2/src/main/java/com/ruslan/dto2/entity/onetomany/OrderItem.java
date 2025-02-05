package com.ruslan.dto2.entity.onetomany;

import com.ruslan.dto2.entity.manytomany.Order;
import com.ruslan.dto2.entity.manytomany.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private Integer countProduct;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
