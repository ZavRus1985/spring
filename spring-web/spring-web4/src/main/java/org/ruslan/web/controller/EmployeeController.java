package org.ruslan.web.controller;

import org.ruslan.web.entity.Employee;
import org.ruslan.web.service.EmployeeAggregatorService;
import org.ruslan.web.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeAggregatorService employeeAggregatorService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeAggregatorService employeeAggregatorService) {
        this.employeeService = employeeService;
        this.employeeAggregatorService = employeeAggregatorService;
    }


    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable Integer id, Model model) {
        model.addAttribute("employees", List.of(employeeService.getEmployeeById(id)));
        return "employees";
    }

    //-------------------------------------
    @GetMapping("/employees/new")
    public String getAddNewEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "new_employee";
    }

    @PostMapping("/employees")
    public String createEmployeeAndRedirect(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    //------------------------------------
    @GetMapping("/employees/{id}/update")
    public String getUpdateEmployeePage(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "update_employee";
    }

    @PutMapping("/employees/{id}")
    public String updateEmployeeAndRedirect(@PathVariable Integer id, @ModelAttribute Employee employee) {
        System.out.println("!!!");
        employeeService.updateEmployee(employee, employee.getId());
        return "redirect:/employees";
    }

    //-------------------------------------
    @GetMapping("/employees/{id}/delete")
    public String getDeleteEmployeePage(@PathVariable Integer id) {
        return "delete_form";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeAndRedirect(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    //-------------------------------------


}
