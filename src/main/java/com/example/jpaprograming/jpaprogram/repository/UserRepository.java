package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
