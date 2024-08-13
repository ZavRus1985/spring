package org.ruslan.web.service;

import org.ruslan.web.entity.Employee;
import org.ruslan.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optional = employeeRepository.getEmployeeById(id);
        return employeeRepository.getEmployeeById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Employee with id='%d' not found".formatted(id)));
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.saveEmployee(employee);
    }

    public void updateEmployee(Employee employee, Integer id) {
        employeeRepository.updateOrder(employee, id);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteEmployee(id);
    }
}
