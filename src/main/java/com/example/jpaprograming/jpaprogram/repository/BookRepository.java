package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
