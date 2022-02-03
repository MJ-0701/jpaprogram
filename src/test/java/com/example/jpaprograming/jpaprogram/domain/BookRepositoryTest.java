package com.example.jpaprograming.jpaprogram.domain;

import com.example.jpaprograming.jpaprogram.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setBookName("JPA 기초");
        book.setAuthor("채명정");

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());

    }

}