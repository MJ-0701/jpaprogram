package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    void crud(){
//        userRepository.save(new User());
////        System.out.println(">>>>" + userRepository.findAll()); // User Entity 모든 데이터를 List 형식으로 가져옴 (현업에서는 성능 ISSUE로 잘 사용하진 않음. -> EX) 회원정보가 1000만건이라 하면 그것들을 다 HEAP 메모리에 저장 해야 되기 때문.
//        userRepository.findAll().forEach(System.out::println);
//    }

    @Test
    void crud(){
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<User> users = userRepository.findAllById(Lists.newArrayList(5L,7L,9L)); // 5 7 9 번 아이디 가져오기
        users.forEach(System.out::println);
    }
}