package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // select * from user where name = ?
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    // select * from user where name = ? and email = ?
    Optional<User> findByNameAndEmail(String name, String email);
}
