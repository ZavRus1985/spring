package com.ruslan.mapping.controller;


import com.ruslan.mapping.dto.DepartmentDto;
import com.ruslan.mapping.dto.ObjAdditionResponse;
import com.ruslan.mapping.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public DepartmentDto getDepartmentIdById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public ObjAdditionResponse saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    //-------------------------------------

}
