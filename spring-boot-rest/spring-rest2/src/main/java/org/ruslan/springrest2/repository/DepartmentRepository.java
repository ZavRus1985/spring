package org.ruslan.springrest2.repository;


import org.ruslan.springrest2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select d from Department d left join fetch d.employees")
    List<Department> findAllDepartments();
}
