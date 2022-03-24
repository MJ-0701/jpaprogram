package com.example.jpaprograming.bookmanager.domain;

import com.example.jpaprograming.bookmanager.domain.listener.Auditable;
import com.example.jpaprograming.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(value = {UserEntityListener.class})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user", indexes = @Index(columnList = "name"), uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User extends BaseEntity{

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

    @Enumerated(EnumType.STRING) // default ORDINAL 인데 ORDINAL 로 하게되면 ENUM 값이 추가되거나 자리가 바뀌게 되면 INDEX 순서가 바귀므로
    // 잠재적인 버그 요소가 될 수 있다. 그렇기 떄문에 반드시 STRING 으로 지정 해야한다.
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // 유저히스토리의 값은 기본적으로 유저 테이블에서 수정이 불가능 해야 하므로 false 처리
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>(); // null point 예방

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();




}
