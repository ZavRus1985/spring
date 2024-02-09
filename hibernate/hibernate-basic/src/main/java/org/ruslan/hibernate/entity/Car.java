package org.ruslan.hibernate.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class Car {
    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Id
    @UuidGenerator
   // @Column(unique = true, nullable = false)
    private String model;
    @Column(nullable = false)
    private BigDecimal price;
    private String owner;
    @Column(nullable = false)
    private Integer year;
    @Column(name = "insurance_expiration_date")
    private LocalDate insuranceExpirationDate;


    public Car(){

    }
    public Car(Long id, String model, BigDecimal price, String owner, Integer year, LocalDate insuranceExpirationDate) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.owner = owner;
        this.year = year;
        this.insuranceExpirationDate = insuranceExpirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getInsuranceExpirationDate() {
        return insuranceExpirationDate;
    }

    public void setInsuranceExpirationDate(LocalDate insuranceExpirationDate) {
        this.insuranceExpirationDate = insuranceExpirationDate;
    }

}
