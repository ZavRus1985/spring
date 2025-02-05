package com.ruslan.dto1.service;

import com.ruslan.dto1.dto.EmployeeAdditionResponse;
import com.ruslan.dto1.dto.EmployeeDto;
import com.ruslan.dto1.entity.Department;
import com.ruslan.dto1.entity.Employee;
import com.ruslan.dto1.repository.DepartmentRepository;
import com.ruslan.dto1.repository.EmployeeRepository;
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
    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAllEmployees()
                .stream()
                .map(employee -> new EmployeeDto(
                        employee.getEmail(),
                        employee.getEmail(),
                        employee.getDepartment().getId(),
                        employee.getDepartment().getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        return new EmployeeDto(employee.getName(),
                employee.getEmail(),
                employee.getDepartment().getId(),
                employee.getDepartment().getName());
    }

    @Transactional
    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());

        Department department = new Department();
        department.setName(employeeDto.getDepartmentName());
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }


    public EmployeeAdditionResponse updateEmployee(EmployeeDto employeeDto, Integer id) {
        Employee updatebableEmployee;
        try {
            updatebableEmployee = employeeRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            return new EmployeeAdditionResponse(
                    false, "Employee not found" + ex.getMessage());
        }
        updatebableEmployee.setName(employeeDto.getName());
        updatebableEmployee.setEmail(employeeDto.getEmail());

        if (employeeDto.getDepartmentName() != null && employeeDto.getDepartmentId() == null) {
            try {
                Department department = departmentService.createDepartment(employeeDto);
                updatebableEmployee.setDepartment(department);
            } catch (NoSuchElementException ex) {
                return new EmployeeAdditionResponse(
                        false, "Error during adding employee to department" + ex.getMessage());
            }
        } else if (employeeDto.getDepartmentName() != null) {
            try {
                Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
                departmentRepository.updateDepartment(employeeDto.getDepartmentName(), department.getId());
                updatebableEmployee.setDepartment(department);
            } catch (NoSuchElementException ex) {
                return new EmployeeAdditionResponse(
                        false, "Error during adding employee to department" + ex.getMessage());
            }
        }

        employeeRepository.updateEmployee(
                updatebableEmployee.getName(),
                updatebableEmployee.getEmail(),
                updatebableEmployee.getDepartment(),
                id);
        return new EmployeeAdditionResponse(true, "Employee updated successfully");
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        int deleted = employeeRepository.deleteEmployeeById(id);
        if (deleted == 0) {
            throw new NoSuchElementException("Employee not found");
        }
    }
}
