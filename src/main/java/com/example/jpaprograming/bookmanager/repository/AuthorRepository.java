package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
