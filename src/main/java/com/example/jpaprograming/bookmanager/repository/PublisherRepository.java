package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
