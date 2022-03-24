package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {


}
