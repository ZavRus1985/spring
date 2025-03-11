package com.ruslan.validation.controller;

import com.ruslan.validation.dto.EmployeeDto;
import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    //-------------------------------------

    @PostMapping
    public void saveEmployee(@Valid @RequestBody EmployeeDto employee) {
        employeeService.saveEmployee(employee);
    }

//    //------------------------------------

    @PutMapping("/{id}")
    public ObjAdditionResponse updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto, id);
    }

//    //-------------------------------------

    @DeleteMapping("/{id}")
    public ObjAdditionResponse deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
