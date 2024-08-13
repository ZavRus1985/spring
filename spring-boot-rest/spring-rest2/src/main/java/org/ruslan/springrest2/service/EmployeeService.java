package org.ruslan.springrest2.service;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2.entity.Employee;
import org.ruslan.springrest2.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAllEmployees();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Transactional
    public void saveEmployee(Employee user) {
        employeeRepository.save(user);
    }

    @Transactional
    public void updateEmployee(Employee employee, Integer id) {
        Employee updatebableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        updatebableEmployee.setName(employee.getName());
        updatebableEmployee.setEmail(employee.getEmail());
        updatebableEmployee.setDepartment(employee.getDepartment());
        employeeRepository.updateEmployee(updatebableEmployee.getName(),
                updatebableEmployee.getEmail(), updatebableEmployee.getDepartment(), id);
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        int deleted = employeeRepository.deleteEmployeeById(id);
        if(deleted == 0) {
            throw new NoSuchElementException("Employee not found");
        }
    }
}
