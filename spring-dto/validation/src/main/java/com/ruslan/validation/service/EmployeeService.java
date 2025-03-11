package com.ruslan.validation.service;


import com.ruslan.validation.dto.EmployeeDto;
import com.ruslan.validation.dto.ObjAdditionResponse;
import com.ruslan.validation.entity.Employee;
import com.ruslan.validation.repository.EmployeeRepository;
import com.ruslan.validation.service.mapper.EmployeeMapper;
import com.ruslan.validation.service.mapper.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
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
                .orElseThrow(() -> new NoSuchEntityException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
    }

    @Transactional
    public ObjAdditionResponse updateEmployee(EmployeeDto employeeDto, Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Employee not found"));

        employeeMapper.update(employee, employeeDto);
        employeeRepository.save(employee);

        return new ObjAdditionResponse(true, "Employee updated successfully");
    }

    @Transactional
    public ObjAdditionResponse deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new NoSuchEntityException("Employee with id=%d not found".formatted(id));
        }
        employeeRepository.deleteById(id);
        return new ObjAdditionResponse(true, "Employee deleted successfully");
    }
}
