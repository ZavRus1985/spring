package org.ruslan.springrest2part2.service;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2part2.entity.Department;
import org.ruslan.springrest2part2.entity.Employee;
import org.ruslan.springrest2part2.repository.DepartmentRepository;
import org.ruslan.springrest2part2.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Department> getAllDepartment() {
        return departmentRepository.findAllDepartments();
    }

    //------------------------------------------------------------------------------------

    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
    }

    //------------------------------------------------------------------------------------

    @Transactional
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    //------------------------------------------------------------------------------------

    @Transactional
    public void updateDepartment(Department department, Integer id) {
        Department updatebableDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        updatebableDepartment.setName(department.getName());
        departmentRepository.updateDepartment(updatebableDepartment.getName(), id);
    }

    @Transactional
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    //------------------------------------------------------------------------------------

    @Transactional
    public void updateDepartmentAddEmployee(Employee employee, Integer id) {

        Department updatebableDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department not found"));

        updatebableDepartment.getEmployees().add(employee);
        departmentRepository.save(updatebableDepartment);
    }

    //------------------------------------------------------------------------------------

    @Transactional
    public void deleteDepartmentFromEmployee(Integer id) {

        Employee updatebableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));

        employeeRepository.deleteDepartmentFromEmployee(updatebableEmployee.getId());
    }

    //------------------------------------------------------------------------------------

    @Transactional
    public void editDepartmentForEmployee(Integer sourceDepartmentId, Integer targetDepartmentId) {

        employeeRepository.editDepartmentForEmployee(sourceDepartmentId, targetDepartmentId);
    }
}