package com.ruslan.mapping.service;

import com.ruslan.mapping.dto.ObjAdditionResponse;
import com.ruslan.mapping.dto.EmployeeDto;
import com.ruslan.mapping.entity.Employee;
import com.ruslan.mapping.repository.EmployeeRepository;
import com.ruslan.mapping.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;


    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAllEmployees()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
    }


    public ObjAdditionResponse updateEmployee(EmployeeDto employeeDto, Integer id) {
        Employee updatebableEmployee;
        try {
            updatebableEmployee = employeeRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            return new ObjAdditionResponse(
                    false, "Employee with id=%d not found".formatted(id) + ex.getMessage());
        }
//            updatebableEmployee.setName(employeeDto.getName());
//            updatebableEmployee.setEmail(employeeDto.getEmail());
//
//        if (employeeDto.getDepartmentName() != null && employeeDto.getDepartmentId() == null) {
//            try {
//                Department department = departmentService.createDepartment(employeeDto);
//                updatebableEmployee.setDepartment(department);
//            } catch (NoSuchElementException ex) {
//                return new ObjAdditionResponse(
//                        false, "Error during adding employee to department" + ex.getMessage());
//            }
//        } else if (employeeDto.getDepartmentName() != null) {
//            try {
//                Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
//                updatebableEmployee.setDepartment(department);
//            } catch (NoSuchElementException ex) {
//                return new ObjAdditionResponse(
//                        false, "Error during adding employee to department" + ex.getMessage());
//            }
//        }
//
//        employeeRepository.updateEmployee(
//                updatebableEmployee.getName(),
//                updatebableEmployee.getEmail(),
//                updatebableEmployee.getDepartment(),
//                id);

        employeeMapper.update(updatebableEmployee, employeeDto);
        employeeRepository.save(updatebableEmployee);

        return new ObjAdditionResponse(true, "Employee updated successfully");
    }

    @Transactional
    public ObjAdditionResponse deleteEmployee(Integer id) {
        if(!employeeRepository.existsById(id)){
            throw new NoSuchElementException("Employee with id=%d not found".formatted(id));
        }
        employeeRepository.deleteById(id);

        return new ObjAdditionResponse(true, "Employee deleted successfully");
    }
}
