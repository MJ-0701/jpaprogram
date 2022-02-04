package com.example.jpaprograming.bookmanager.domain.listener;

import com.example.jpaprograming.bookmanager.domain.User;
import com.example.jpaprograming.bookmanager.domain.UserHistory;
import com.example.jpaprograming.bookmanager.repository.UserHistoryRepository;
import com.example.jpaprograming.bookmanager.support.Beanutils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class UserEntityListener {
    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o){
        UserHistoryRepository userHistoryRepository = Beanutils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();

        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
