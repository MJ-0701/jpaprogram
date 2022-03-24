package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
