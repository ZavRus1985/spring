package com.ruslan.springsecurity.repository;

import com.ruslan.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name IN :names")
    List<Role> findByNames(List<String> names);
}
