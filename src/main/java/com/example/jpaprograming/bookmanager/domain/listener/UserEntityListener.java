package com.example.jpaprograming.bookmanager.domain.listener;

import com.example.jpaprograming.bookmanager.domain.User;
import com.example.jpaprograming.bookmanager.domain.UserHistory;
import com.example.jpaprograming.bookmanager.repository.UserHistoryRepository;
import com.example.jpaprograming.bookmanager.support.Beanutils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){
        UserHistoryRepository userHistoryRepository = Beanutils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
