package com.ruslan.validation.controller;


import com.ruslan.validation.dto.DepartmentDto;
import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.service.DepartmentService;
import jakarta.validation.Valid;
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
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}")
    public DepartmentDto getDepartmentIdById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public ObjAdditionResponse saveDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    @PutMapping("/{id}")
    public ObjAdditionResponse updateDepartment(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto) {
        return departmentService.update(departmentDto, id);
    }

    @DeleteMapping
    public ObjAdditionResponse deleteDepartment(@PathVariable Integer id) {
        return departmentService.delete(id);
    }
}
