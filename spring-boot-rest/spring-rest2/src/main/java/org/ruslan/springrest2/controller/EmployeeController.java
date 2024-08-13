package org.ruslan.springrest2.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2.entity.Employee;
import org.ruslan.springrest2.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllDepartments() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{userId}")
    public Employee getEmployeeById(@PathVariable Integer userId) {
        return employeeService.getEmployeeById(userId);
    }

    //-------------------------------------

    @PostMapping("/save")
    public void saveDepartment(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }

    //------------------------------------

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Integer id, @RequestBody Employee employee) {
        employeeService.updateEmployee(employee, id);
    }

    //-------------------------------------

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
