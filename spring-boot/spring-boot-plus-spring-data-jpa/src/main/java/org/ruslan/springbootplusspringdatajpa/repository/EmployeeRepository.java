package org.ruslan.springbootplusspringdatajpa.repository;

import org.ruslan.springbootplusspringdatajpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface  EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findAll();

    public Optional<Employee> findById(Integer id);

    @Modifying
    @Transactional
    @Query("delete from Employee e where e.id = :id")
    int deleteEmployeeById(Integer id);
}
