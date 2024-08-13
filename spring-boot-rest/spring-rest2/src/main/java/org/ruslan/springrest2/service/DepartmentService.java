package org.ruslan.springrest2.service;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2.entity.Department;
import org.ruslan.springrest2.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAllDepartment() {
        return departmentRepository.findAllDepartments();
    }

    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
    }
}
