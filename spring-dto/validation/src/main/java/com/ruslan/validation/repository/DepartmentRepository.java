package com.ruslan.validation.repository;

import com.ruslan.validation.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select d from Department d left join fetch d.employees")
    List<Department> findAllDepartments();

    @Modifying
    @Transactional
    @Query("UPDATE Department SET name = :name where id = :id")
    int updateDepartment(String name, Integer id);
}
