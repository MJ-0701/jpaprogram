package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserHistoryRepositoryTest {

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void userHistoryTest(){
        User user = new User();

        user.setEmail("jack2718-new@naver.com");
        user.setName("new-mj");

        userRepository.save(user);

        user.setName("new-new-mj");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

    }

}