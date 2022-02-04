package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {


}
