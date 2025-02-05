package com.ruslan.dto1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(cascade = {PERSIST, MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();
}
