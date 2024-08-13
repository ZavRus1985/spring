package org.ruslan.springrest2.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2.entity.Department;
import org.ruslan.springrest2.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentIdById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    //-------------------------------------

}
