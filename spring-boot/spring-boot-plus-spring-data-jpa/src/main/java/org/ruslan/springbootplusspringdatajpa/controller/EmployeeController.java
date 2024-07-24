
package org.ruslan.springbootplusspringdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springbootplusspringdatajpa.entity.Employee;
import org.ruslan.springbootplusspringdatajpa.service.EmployeeAggregatorService;
import org.ruslan.springbootplusspringdatajpa.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeAggregatorService employeeAggregatorService;

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAll());
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
        return "employee_form";
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
        return "employee_form";
    }

    @PutMapping("/employees/{id}")
    public String updateEmployeeAndRedirect(@PathVariable Integer id, @ModelAttribute Employee employee) {
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