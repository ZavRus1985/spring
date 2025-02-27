package com.ruslan.mapping.controller;

import com.ruslan.mapping.dto.ObjAdditionResponse;
import com.ruslan.mapping.dto.EmployeeDto;
import com.ruslan.mapping.service.EmployeeService;
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

    @PostMapping
    public void saveDepartment(@RequestBody EmployeeDto employee) {
        employeeService.saveEmployee(employee);
    }

//    //------------------------------------

    @PutMapping("/{id}")
    public ObjAdditionResponse updateDepartment(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }

//    //-------------------------------------

    @DeleteMapping("/{id}")
    public ObjAdditionResponse deleteDepartment(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
