package com.ruslan.dto1.controller;

import com.ruslan.dto1.dto.EmployeeAdditionResponse;
import com.ruslan.dto1.dto.EmployeeDto;
import com.ruslan.dto1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllDepartments() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{userId}")
    public EmployeeDto getEmployeeById(@PathVariable Integer userId) {
        return employeeService.getEmployeeById(userId);
    }

    //-------------------------------------

    @PostMapping("/save")
    public void saveDepartment(@RequestBody EmployeeDto employee) {
        employeeService.saveEmployee(employee);
    }

//    //------------------------------------

    @PutMapping("/{id}")
    public EmployeeAdditionResponse updateDepartment(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }

//    //-------------------------------------

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
