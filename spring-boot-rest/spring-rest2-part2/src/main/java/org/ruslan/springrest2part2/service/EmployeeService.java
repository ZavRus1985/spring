package org.ruslan.springrest2part2.service;


import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2part2.entity.Employee;
import org.ruslan.springrest2part2.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void deleteDepartmentFromEmployee(Integer id) {

        Employee updatebableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));

        employeeRepository.deleteDepartmentFromEmployee(updatebableEmployee.getId());
    }
}
