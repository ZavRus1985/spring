package org.ruslan.springrest2part2.controller;

import lombok.RequiredArgsConstructor;
import org.ruslan.springrest2part2.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    //4.Добавить API:
    // для удаления работника из отдела


    @DeleteMapping("/{id}/delete-department")
    public void deleteDepartmentFromEmployee(@PathVariable Integer id) {
        employeeService.deleteDepartmentFromEmployee(id);
    }
}
