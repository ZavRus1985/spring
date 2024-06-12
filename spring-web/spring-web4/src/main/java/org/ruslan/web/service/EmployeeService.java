package org.ruslan.web.service;

import org.ruslan.web.entity.Employee;
import org.ruslan.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository er;

    @Autowired
    public EmployeeService(EmployeeRepository er) {
        this.er = er;
    }

    public List<Employee> getAllEmployees() {
        return er.getAllEmployees();
    }

    public Employee getEmployeeById(Integer id) {
        return er.getEmployeeById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Employee with id='%d' not found".formatted(id)));
    }

    public void saveEmployee(Employee employee) {
        er.saveEmployee(employee);
    }

    public void updateEmployee(Employee employee, Integer id) {
        er.updateOrder(employee, id);
    }

    public void deleteEmployee(Integer employeeId){
        er.deleteEmployee(employeeId);
    }
}
