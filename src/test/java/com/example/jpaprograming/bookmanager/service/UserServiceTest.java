package com.example.jpaprograming.bookmanager.service;

import com.example.jpaprograming.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void userTest(){
        userService.put();

        System.out.println(">>>>" + userRepository.findByEmail("coaudwjd@coaudwjd.com"));

    }

}