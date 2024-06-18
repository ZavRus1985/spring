package org.ruslan.web.controller;

import org.ruslan.web.model.EmployeeAgregation;
import org.ruslan.web.service.EmployeeAggregatorService;
import org.ruslan.web.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeAggregatorController {

    private final EmployeeAggregatorService employeeAggregatorService;

    @Autowired
    public EmployeeAggregatorController(EmployeeAggregatorService employeeAggregatorService) {
        this.employeeAggregatorService = employeeAggregatorService;
    }

//   Добавить метод, группирующий работников по отделам с агрегацией по средней зарплате
// Такой список (отдел – зарплата) необходимо отображать на HTML-странице.
// Выбор должен осуществляться с помощью параметра запроса


    @GetMapping("/employees/stat") // statistics
    public String getStatisticEmployeePage() {

        return "menu_statitistic";
    }

    @PostMapping("/employees/{operation}")
    public String averageSalaryByDepartmentPage(@PathVariable String operation, Model model) {

        List<EmployeeAgregation> employees;
        switch (operation){
            case "avg":
                employees = employeeAggregatorService.aggregationByAverageSalary();
                model.addAttribute("employees", employees);
                break;
            case "max":
                employees = employeeAggregatorService.aggregationByMaxSalary();
                model.addAttribute("employees", employees);
                break;
            case "min":
                employees = employeeAggregatorService.aggregationByMinSalary();
                model.addAttribute("employees", employees);
                break;
            case "sum":
                employees = employeeAggregatorService.aggregationBySumSalary();
                model.addAttribute("employees", employees);
                break;
        }

        return "average_by_department";
    }
}
