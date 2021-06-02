package com.example.jpaprograming.jpaprogram.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test(){
        User user = new User();

        user.setEmail("jack2718@naver.com");
        user.setName("채명정");
        System.out.println(">>>>>" + user);
    }

}