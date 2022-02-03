package com.example.jpaprograming.jpaprogram.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(value = EntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user", indexes = @Index(columnList = "name"), uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User implements Auditable {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String email;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING) // default ORDINAL 인데 ORDINAL 로 하게되면 ENUM 값이 추가되거나 자리가 바뀌게 되면 INDEX 순서가 바귀므로
    // 잠재적인 버그 요소가 될 수 있다. 그렇기 떄문에 반드시 STRING 으로 지정 해야한다.
    private Gender gender;

    @Transient
    private String testData;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> addresses;

//    // Entity listener
//    @PrePersist // method 가 실행되기 이전
//    public void prePersist(){
//        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now(); // 데이터의 정확성을 위해서 insert 가 되기 직전에 설정을 해준다.
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PostPersist // method 가 실행되기 이후
//    public void postPersist(){
//        System.out.println(">>> postPersist");
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>> postUpdate");
//    }
//
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>> preRemove");
//    }
//
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>> postRemove");
//    }
//
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>> postLoad");
//    }


}
