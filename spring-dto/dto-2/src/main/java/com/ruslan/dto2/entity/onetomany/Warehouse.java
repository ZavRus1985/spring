package com.ruslan.dto2.entity.onetomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruslan.dto2.entity.manytomany.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "warehouses")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Warehouse {

    @Id
    private final Integer id = 1;

//    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

}
