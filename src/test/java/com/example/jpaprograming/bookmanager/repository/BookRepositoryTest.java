package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.Book;
import com.example.jpaprograming.bookmanager.domain.Publisher;
import com.example.jpaprograming.bookmanager.domain.Review;
import com.example.jpaprograming.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setBookName("JPA 기초");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());

    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("jack2718@naver.com");
        System.out.println("Review : " + user.getReviews());
        System.out.println("Book :" + user.getReviews().get(0).getBook());
        System.out.println("Publisher :" + user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview(){
        givenReview(givenUser(),givenBook(givenPublisher()));
    }

    private User givenUser(){
        return userRepository.findByEmail("jack2718@naver.com");
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("출판사 이름");

        return publisherRepository.save(publisher);
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();

        book.setBookName("책이름");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private void givenReview(User user, Book book) {

        Review review = new Review();

        review.setTitle("리뷰 타이틀");
        review.setContent("리뷰 내용");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }
}