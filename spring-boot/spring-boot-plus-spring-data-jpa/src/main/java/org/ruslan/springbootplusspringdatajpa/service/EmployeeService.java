package org.ruslan.springbootplusspringdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.ruslan.springbootplusspringdatajpa.entity.Employee;
import org.ruslan.springbootplusspringdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(Employee employee, Integer id) {
        Employee updatebableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
                updatebableEmployee.setName(employee.getName());
                updatebableEmployee.setDepartment(employee.getDepartment());
                updatebableEmployee.setSalary(employee.getSalary());
                employeeRepository.save(updatebableEmployee);
    }

    public void deleteEmployee(Integer id) {
        int deleted = employeeRepository.deleteEmployeeById(id);

        if(deleted == 0) {
            throw new NoSuchElementException("Employee not found");
        }
    }
}
