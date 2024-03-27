package org.ruslan.hib.entity.onetomany;


import jakarta.persistence.*;
import org.ruslan.hib.entity.manytomany.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producers")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Product> products = new ArrayList<>();


    public Producer() {
    }

    public Producer(Integer id, String name) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        for (Product product: products) {
            product.setProducer(this);
        }
        this.products = products;
    }


}
