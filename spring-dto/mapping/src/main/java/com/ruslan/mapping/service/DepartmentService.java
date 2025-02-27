package com.ruslan.mapping.service;

import com.ruslan.mapping.dto.DepartmentDto;
import com.ruslan.mapping.dto.EmployeeDto;
import com.ruslan.mapping.dto.ObjAdditionResponse;
import com.ruslan.mapping.entity.Department;
import com.ruslan.mapping.repository.DepartmentRepository;
import com.ruslan.mapping.service.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository ;
    private final DepartmentMapper departmentMapper;

    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartment() {
        return departmentRepository.findAllDepartments()
                .stream()
                .map(department -> new DepartmentDto(department.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Integer id) {
        Department department =  departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        return new DepartmentDto(department.getName());
    }

    @Transactional()
    public ObjAdditionResponse save(DepartmentDto departmentDto) {
        Department department;
        try {
            department =  departmentMapper.toEntity(departmentDto);
        }
        catch (NoSuchElementException ex) {
                return new ObjAdditionResponse(
                        false, "Error during mapping department" + ex.getMessage());
            }
        departmentRepository.save(department);
        return new ObjAdditionResponse(true, "Department saved");
    }

    public Department createDepartment(EmployeeDto employeeDto) {
        Department department = new Department();
        department.setName(employeeDto.getDepartmentName());
        departmentRepository.save(department);
        return department;
    }
}
