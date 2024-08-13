package org.ruslan.springrest2part2.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2part2.entity.Department;
import org.ruslan.springrest2part2.entity.Employee;
import org.ruslan.springrest2part2.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentIdById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    //-------------------------------------

    @PostMapping("/save")
    public void saveDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
    }

    //------------------------------------


    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        departmentService.updateDepartment(department, id);
    }

    //-------------------------------------

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }

    //-------------------------------------
    //4.Добавить API:
    // для добавления работника в отдел,
    // для удаления работника из отдела,
    // для перевода всех сотрудников из одного отдела в другой.
    //-------------------------------------

    @PutMapping("/{id}/addEmployee")
    public void updateDepartmentAddEmployee(@PathVariable Integer id,
                                            @RequestBody Employee employee) {
        departmentService.updateDepartmentAddEmployee(employee, id);
    }

    //-------------------------------------

    @DeleteMapping("/{id}/deleteDepartment")
    public void deleteDepartmentFromEmployee(@PathVariable Integer id) {
        departmentService.deleteDepartmentFromEmployee(id);
    }

    //-------------------------------------

    @PutMapping("/{sourceDepartmentId}/employees/{targetDepartmentId}")
    public void editDepartmentForEmployee(@PathVariable Integer sourceDepartmentId,
                                          @PathVariable Integer targetDepartmentId) {
        departmentService.editDepartmentForEmployee(sourceDepartmentId, targetDepartmentId);
    }

    //-------------------------------------

}
