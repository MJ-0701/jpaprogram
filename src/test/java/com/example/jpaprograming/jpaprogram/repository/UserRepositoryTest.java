package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest                                                                 // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
@DisplayName("JPA 외래키 테스트")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    void crud(){
//        userRepository.save(new User());
//        System.out.println(">>>>" + userRepository.findAll()); // User Entity 모든 데이터를 List 형식으로 가져옴 (현업에서는 성능 ISSUE로 잘 사용하진 않음. -> EX) 회원정보가 1000만건이라 하면 그것들을 다 HEAP 메모리에 저장 해야 되기 때문.
//    }

    @Test
    public void create(){
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail("jack2718@naver.com");
        user.setName("채명정");
        User newUser = userRepository.save(user);

        System.out.println("newUser :" + newUser);
    }

}