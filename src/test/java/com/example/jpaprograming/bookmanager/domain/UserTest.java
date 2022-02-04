package com.example.jpaprograming.bookmanager.domain;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void test(){
        User user = new User();

        user.setEmail("jack2718@naver.com");
        user.setName("채명정");
        System.out.println(">>>>>" + user);
    }

}