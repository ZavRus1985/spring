package org.ruslan.web.model;


import org.springframework.stereotype.Component;


public class EmployeeAgregation {

    private String department;
    private Double statValue;

    public EmployeeAgregation(String department, Double statValue) {
        this.department = department;
        this.statValue = statValue;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getStatValue() {
        return statValue;
    }

    public void setStatValue(Double statValue) {
        this.statValue = statValue;
    }
}
