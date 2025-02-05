package com.ruslan.dto1.service;

import com.ruslan.dto1.dto.DepartmentDto;
import com.ruslan.dto1.dto.EmployeeDto;
import com.ruslan.dto1.entity.Department;
import com.ruslan.dto1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository ;

    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartment() {
        return departmentRepository.findAllDepartments()
                .stream()
                .map(department -> new DepartmentDto(department.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
    }

    public Department createDepartment(EmployeeDto employeeDto) {
        Department department = new Department();
        department.setName(employeeDto.getDepartmentName());
        departmentRepository.save(department);
        return department;
    }
}
