package com.example.jpaprograming.bookmanager.service;

import com.example.jpaprograming.bookmanager.domain.User;
import com.example.jpaprograming.bookmanager.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityMangerTest {

    @Autowired // Junit5 에서는 생성자로 주입이 안됨. -> Junit5 가 스스로 DI 를 지원하는데 지원하는 타입이 정해져 있어서 Junit이 생성자에 다른 의존성을 주입하려고 먼저 개입을 하기 때문.
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void entityMangerTest(){
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    void cacheFindTest(){
//        System.out.println(userRepository.findByEmail("jack2718@naver.com"));
//        System.out.println(userRepository.findByEmail("jack2718@naver.com"));
//        System.out.println(userRepository.findByEmail("jack2718@naver.com"));

        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
    }

    @Test
    void cacheFindTest2(){
        User user = userRepository.findById(1L).get();

        user.setName("채명정1");

        userRepository.save(user);

        System.out.println("-----------------");

        user.setEmail("jack2718@nnaverr.com");

        userRepository.save(user);

        userRepository.flush();

    }
}
