package org.ruslan.springrest2part2.repository;


import org.ruslan.springrest2part2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE employees SET department_id = NULL where id = :id")
    int deleteDepartmentFromEmployee(Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
        UPDATE employees 
        SET department_id = :targetDepartmentId where department_id = :sourceDepartmentId
    """)
    int editDepartmentForEmployee(Integer sourceDepartmentId, Integer targetDepartmentId);

}
