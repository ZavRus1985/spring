package org.ruslan.springbootplusspringdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation;
import org.ruslan.springbootplusspringdatajpa.service.EmployeeAggregatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmployeeAggregatorController {

    private final EmployeeAggregatorService employeeAggregatorService;

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
                employees = employeeAggregatorService.getAllAverageBySalary();
                model.addAttribute("employees", employees);
                break;
            case "max":
                employees = employeeAggregatorService.getAllAverageByMax();
                model.addAttribute("employees", employees);
                break;
            case "min":
                employees = employeeAggregatorService.getAllAverageByMin();
                model.addAttribute("employees", employees);
                break;
            case "sum":
                employees = employeeAggregatorService.getAllAverageBySum();
                model.addAttribute("employees", employees);
                break;
        }

        return "average_by_department";
    }
}
