package com.example.jpaprograming.jpaprogram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql , mariadb
//    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
