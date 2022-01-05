package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    void crud(){
//        userRepository.save(new User());
////        System.out.println(">>>>" + userRepository.findAll()); // User Entity 모든 데이터를 List 형식으로 가져옴 (현업에서는 성능 ISSUE로 잘 사용하진 않음. -> EX) 회원정보가 1000만건이라 하면 그것들을 다 HEAP 메모리에 저장 해야 되기 때문.
//        userRepository.findAll().forEach(System.out::println); // -> 개행 하여 출력
//        /*
//        * for (User user : userRepository.findAll()){ 위의 한줄 코드와 같음.
//        *   System.out.println(user);
//        * }*/
//    }

//    @Test
//    void crud(){
////        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L,3L,5L)); // 5 7 9 번 아이디 가져오기
//        users.forEach(System.out::println);
//    }

    @Test
    void crud(){
        userRepository.save(new User(6L,"newMj","jack2718@naver.com",LocalDateTime.now(),null));

        userRepository.flush(); // saveAndFlush 와 같음.

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count(){
        long count = userRepository.count();
        System.out.println(count);

        boolean exists = userRepository.existsById(1L);
        System.out.println(exists);
    }

    @Test
    void paging(){
        Page<User> users = userRepository.findAll(PageRequest.of(1,3)); // page : 1번 인덱스(0부터 시작하므로 1번인덱스는 2페이지) size :1페이지에 3개씩 노출
        System.out.println("page :"+users);
        System.out.println("total elements :"+ users.getTotalElements()); // 5개가 입력 됐기 때문에 5개
        System.out.println("total pages :" + users.getTotalPages()); // 전체 페이지의 갯수 -> 1페이지에 3개씩 노출 3/2
        System.out.println("number of elements :" + users.getNumberOfElements()); // 현재 가져온 레코드의 갯수 // 1번 페이지 컨텐츠 2개(0번 페이지 1번 페이지)
        System.out.println("sort :" +users.getSort()); // 지정 안해줬기 때문에 unsorted
        System.out.println("size :" +users.getSize()); // 페이징을 할때 나누는 크기

        users.getContent().forEach(System.out::println);

    }

    @Test
    void queryByExample(){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // name 은 매칭하지 않음.
                .withMatcher("email", endsWith()); // email 은 매칭함. ==> like

        Example<User> example = Example.of(new User("ma", "naver.com"), matcher); // like %'naver.com'% ==> name 컬럼은 where 조건절에 들어가지 않고 email 값만 like로 찾음.
        userRepository.findAll(example).forEach(System.out::println);

    }
}