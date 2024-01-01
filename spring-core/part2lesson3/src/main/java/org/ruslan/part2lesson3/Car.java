package org.ruslan.part2lesson3;

import java.math.BigDecimal;
import java.util.Objects;

public class Car {

    private Long id;
    private String model;
    private BigDecimal price;
    private String owner;
    private Integer year;

    public Car(Long id, String model, BigDecimal price, String owner, Integer year) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.owner = owner;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public Integer getYear() {
        return year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", owner='" + owner + '\'' +
                ", year=" + year +
                '}';
    }
}
