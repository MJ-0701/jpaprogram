package com.example.jpaprograming.jpaprogram.repository;

import com.example.jpaprograming.jpaprogram.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime yesterday);

    List<User> findByCreatedAtBefore(LocalDateTime yesterday);

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByIdGreaterThan(Long id);

    List<User> findByIdGreaterThanEqual(Long id);

    List<User> findByIdLessThan(Long id);

    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<User> findByIdBetween(Long id1, Long id2);

    List<User> findByIdIsNotNull();

    List<User> findByAddressesIsNotEmpty();

    // In
    List<User> findByNameIn(List<String> names); // 인절이기 때문에 이터레이터 타입인 리스트가 파라미터로 들어가고 제네릭 값으로 네임의 타입인 String 이 들어간다.

    // Like
    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);

    // Sorting
    List<User> findTop1ByName(String name);

    List<User> findLast1ByName(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);

    List<User> findTop2ByName(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name); // 이름으로 검색하고 id 역순 email 정순으로 결과값 출력

    List<User> findFirstByName(String name, Sort sort);

    // Paging
    Page<User> findByName(String name, Pageable pageable);





}
