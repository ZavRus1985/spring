package org.ruslan.web.controller;

import org.ruslan.web.entity.Employee;
import org.ruslan.web.service.EmpAggregatorService;
import org.ruslan.web.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService es;
    private final EmpAggregatorService eas;

    @Autowired
    public EmployeeController(EmployeeService es, EmpAggregatorService eas) {
        this.es = es;
        this.eas = eas;
    }


    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", es.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/{employeeId}")
    public String getEmployeeById(@PathVariable Integer employeeId, Model model) {
        model.addAttribute("employee", es.getEmployeeById(employeeId));
        return "employee_by_id";
    }

    //-------------------------------------
    @GetMapping("/employees/new")
    public String getAddNewEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "new_employee";
    }

    @PostMapping("/employees")
    public String createEmployeeAndRedirect(@ModelAttribute Employee employee) {
        es.saveEmployee(employee);
        return "redirect:/employees";
    }

    //------------------------------------
    @GetMapping("/employees/update/{id}")
    public String getUpdateEmployeePage(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", new Employee());
        return "update_employee";
    }

    @PutMapping("/updEmployees/{id}")
    public String updateEmployeeAndRedirect(@PathVariable Integer id, @ModelAttribute Employee employee) {
        es.updateEmployee(employee, employee.getId());
        return "redirect:/employees";
    }

    //-------------------------------------
    @GetMapping("/employees/delete/{id}")
    public String getDeleteEmployeePage(@PathVariable Integer id) {
        return "delete_form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployeeAndRedirect(@PathVariable Integer id) {
        es.deleteEmployee(id);
        return "redirect:/employees";
    }

    //-------------------------------------

//   Добавить метод, группирующий работников по отделам с агрегацией по средней зарплате
// Такой список (отдел – зарплата) необходимо отображать на HTML-странице.
// Выбор должен осуществляться с помощью параметра запроса

    @GetMapping("/employees/stat") // statistics
    public String getStatisticEmployeePage() {

        return "menu_statitistic";
    }

    @PostMapping("/employees/average")
    public String averageSalaryByDepartmentPage(Model model) {
        List<Object[]> employees = eas.aggregationByAverageSalary();
        model.addAttribute("employee", employees);
        return "average_by_department";
    }
}
