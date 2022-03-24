package com.example.jpaprograming.bookmanager.domain;

import com.example.jpaprograming.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@EntityListeners(value = AuditingEntityListener.class)
@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String category;

    private Long authorId;

//    private Long publisherId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude // 안할시 stackoverflow 에러 발생 -> 엔티티 릴레이션을 사용하는 경우 특히 toString 같은 메소드는 순환 참조가 걸리게 되는데 특별히 필요한 경우가 아니라면 릴레이션은 단반향으로 걸거나 투스트링에서 제외 하는 처리가 필요함.
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }







}
