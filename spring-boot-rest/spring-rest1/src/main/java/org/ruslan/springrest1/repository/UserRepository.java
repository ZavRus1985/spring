package org.ruslan.springrest1.repository;

import org.ruslan.springrest1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findById(Integer id);

    @Modifying
    @Transactional
    @Query("delete from User u where u.id = :id")
    int deleteUserById(Integer id);
}
