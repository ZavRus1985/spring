package com.ruslan.mapping.repository;


import com.ruslan.mapping.entity.Department;
import com.ruslan.mapping.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e left join fetch e.department")
    List<Employee> findAllEmployees();

    @Modifying
    @Transactional
    @Query("UPDATE Employee SET name = :name, email = :email, department = :department where id = :id")
    int updateEmployee(String name, String email, Department department, Integer id);

    @Modifying
    @Transactional
    @Query("delete from Employee e where e.id = :id")
    int deleteEmployeeById(Integer id);
}
