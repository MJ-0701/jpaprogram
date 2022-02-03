package com.example.jpaprograming.jpaprogram.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(value = EntityListener.class)
@Entity
@NoArgsConstructor
@Data
public class Book implements Auditable {

    @Id
    @GeneratedValue
    private Long id;

    private String bookName;

    private String author;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        this.updatedAt = LocalDateTime.now();
//    }
}
