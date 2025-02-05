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

    @GetMapping("/{id}")
    public Department getDepartmentIdById(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    //-------------------------------------

    @PostMapping()
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
    // для перевода всех сотрудников из одного отдела в другой.
    //-------------------------------------

    @PutMapping("/{id}/add-employee")
    public void addEmployeeToDepartment(@PathVariable Integer id,
                                            @RequestBody Employee employee) {
        departmentService.updateDepartmentAddEmployee(employee, id);
    }

    //-------------------------------------

    // PUT   api/departments/transfer-employees?source-department-id=1&target-department-id=2
    @PutMapping("/transfer-employees")
    public void transferEmployeesToDepartment(@RequestParam(name = "source-department-id") Integer sourceDepartmentId,
                                              @RequestParam(name = "target-department-id") Integer targetDepartmentId) {
        departmentService.editDepartmentForEmployee(sourceDepartmentId, targetDepartmentId);
    }

    //-------------------------------------

}
