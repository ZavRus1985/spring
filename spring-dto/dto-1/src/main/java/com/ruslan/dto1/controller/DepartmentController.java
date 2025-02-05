package com.ruslan.dto1.controller;

import com.ruslan.dto1.dto.DepartmentDto;
import com.ruslan.dto1.entity.Department;
import com.ruslan.dto1.service.DepartmentService;
import lombok.RequiredArgsConstructor;
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
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentIdById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    //-------------------------------------

}
