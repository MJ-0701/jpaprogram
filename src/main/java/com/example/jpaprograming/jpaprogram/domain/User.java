package com.example.jpaprograming.jpaprogram.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user", indexes = @Index(columnList = "name"), uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

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

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING) // default ORDINAL 인데 ORDINAL 로 하게되면 ENUM 값이 추가되거나 자리가 바뀌게 되면 INDEX 순서가 바귀므로
    // 잠재적인 버그 요소가 될 수 있다. 그렇기 떄문에 반드시 STRING 으로 지정 해야한다.
    private Gender gender;

    @Transient
    private String testData;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> addresses;
}
