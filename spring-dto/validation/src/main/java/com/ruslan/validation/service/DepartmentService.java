package com.ruslan.validation.service;


import com.ruslan.validation.dto.DepartmentDto;
import com.ruslan.validation.dto.EmployeeDto;
import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.entity.Department;
import com.ruslan.validation.repository.DepartmentRepository;
import com.ruslan.validation.service.mapper.DepartmentMapper;
import com.ruslan.validation.service.mapper.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository ;
    private final DepartmentMapper departmentMapper;

    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAllDepartments()
                .stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Integer id) {
        Department department =  departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Department not found"));
        return new DepartmentDto(department.getName());
    }

    @Transactional()
    public ObjAdditionResponse save(DepartmentDto departmentDto) {
        Department department;
        try {
            department =  departmentMapper.toEntity(departmentDto);
        }
        catch (NoSuchEntityException ex) {
                return new ObjAdditionResponse(
                        false, "Error during mapping department" + ex.getMessage());
            }
        departmentRepository.save(department);
        return new ObjAdditionResponse(true, "Department saved");
    }

    @Transactional()
    public ObjAdditionResponse update(DepartmentDto departmentDto, Integer id) {
        Department department;

            department =  departmentRepository.findById(id)
                    .orElseThrow(() -> new NoSuchEntityException("Department not found"));

        departmentMapper.update(department, departmentDto);
        departmentRepository.save(department);

        return new ObjAdditionResponse(true, "Department updated");
    }

    @Transactional()
    public ObjAdditionResponse delete(Integer id) {
        if (!departmentRepository.existsById(id)) {
            return new ObjAdditionResponse(false, "Department with id=%d not found".formatted(id));
        }
        departmentRepository.deleteById(id);
        return new ObjAdditionResponse(true, "Department deleted");
    }

    public Department createDepartment(EmployeeDto employeeDto) {
        Department department = new Department();
        department.setName(employeeDto.getDepartmentName());
        departmentRepository.save(department);
        return department;
    }
}
