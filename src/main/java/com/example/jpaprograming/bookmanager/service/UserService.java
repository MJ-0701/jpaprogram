package com.example.jpaprograming.bookmanager.service;

import com.example.jpaprograming.bookmanager.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;

    @Transactional
    public void put(){
        User user = new User();
        user.setName("newUser");
        user.setEmail("coaudwjd@coaudwjd.com");

        entityManager.persist(user);
        //detached 준 영속 상태.
        entityManager.detach(user);

        user.setName("newUserAfter"); // 해당 메서드를 실행하는 시점에서 바로 쿼리가 실행되는것이 아니므로 트랜잭션이 종료되는 시점에 update 쿼리를 실행해 줌으로서 이후 별도의 save 메서드가 없어도 변경이 반영된다.
        // 엔티티의 더티체크(변경감지) 대량의 엔티티를 다루게 될 경우 로직의 성능저하가 발생함.
        entityManager.merge(user); // detached 상태일때는 준 영속상태라 반영이 안되지만 merge 메소드를 통해 반영해 줄 수 있다.

        entityManager.clear(); // detached 메서드를 반영하려면 클리어 메서드를 호출하기 전에 flush 메서드를 호출해서 반드시 반영을 해줘야됨.






    }
}
