package com.example.jpaprograming.bookmanager.repository;

import com.example.jpaprograming.bookmanager.domain.Gender;
import com.example.jpaprograming.bookmanager.domain.User;
import com.example.jpaprograming.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

//    @Test
//    void crud(){
//        userRepository.save(new User());
////        System.out.println(">>>>" + userRepository.findAll()); // User Entity 모든 데이터를 List 형식으로 가져옴 (현업에서는 성능 ISSUE로 잘 사용하진 않음. -> EX) 회원정보가 1000만건이라 하면 그것들을 다 HEAP 메모리에 저장 해야 되기 때문.
//        userRepository.findAll().forEach(System.out::println); // -> 개행 하여 출력
//        /*
//        * for (User user : userRepository.findAll()){ 위의 한줄 코드와 같음.
//        *   System.out.println(user);
//        * }*/
//    }

//    @Test
//    void crud(){
////        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L,3L,5L)); // 5 7 9 번 아이디 가져오기
//        users.forEach(System.out::println);
//    }

    @Test
    void crud(){
//        userRepository.save(new User(6L,"newMj","jack2718@naver.com",LocalDateTime.now(),null,Gender.MALE,null,null));

        userRepository.flush(); // saveAndFlush 와 같음.

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count(){
        long count = userRepository.count();
        System.out.println(count);

        boolean exists = userRepository.existsById(1L);
        System.out.println(exists);
    }

    @Test
    void paging(){
        Page<User> users = userRepository.findAll(PageRequest.of(1,3)); // page : 1번 인덱스(0부터 시작하므로 1번인덱스는 2페이지) size :1페이지에 3개씩 노출
        System.out.println("page :"+users);
        System.out.println("total elements :"+ users.getTotalElements()); // 5개가 입력 됐기 때문에 5개
        System.out.println("total pages :" + users.getTotalPages()); // 전체 페이지의 갯수 -> 1페이지에 3개씩 노출 3/2
        System.out.println("number of elements :" + users.getNumberOfElements()); // 현재 가져온 레코드의 갯수 // 1번 페이지 컨텐츠 2개(0번 페이지 1번 페이지)
        System.out.println("sort :" +users.getSort()); // 지정 안해줬기 때문에 unsorted
        System.out.println("size :" +users.getSize()); // 페이징을 할때 나누는 크기

        users.getContent().forEach(System.out::println);

    }

    @Test
    void queryByExample(){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // name 은 매칭하지 않음.
                .withMatcher("email", endsWith()); // email 은 매칭함. ==> like

        Example<User> example = Example.of(new User("ma", "naver.com"), matcher); // like %'naver.com'% ==> name 컬럼은 where 조건절에 들어가지 않고 email 값만 like로 찾음.
        userRepository.findAll(example).forEach(System.out::println);
    }

//    @Test
//    void updateQuery(){
//        userRepository.save(new User("mjmj", "coaudwjd@gmail.com"));
//
//        User user = userRepository.findById(5L).orElseThrow(RuntimeException::new);
//
//        user.setEmail("coaduwjd@google.com");
//
//        userRepository.save(user);
//
//        userRepository.findAll().forEach(System.out::println);
//    }

    @Test
    void select(){

        System.out.println("find by id :" + userRepository.findById(1L));
        // and 조건절
        System.out.println("and :" + userRepository.findByEmailAndName("jack2718@naver.com","채명정"));

        // or 조건절
        System.out.println("or :" + userRepository.findByEmailOrName("jack2718@naver.com", "jack"));

        // 시간에 대한 조건(값비교)
        // after
        System.out.println("after :" + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L))); // 어제 이후의 값 => 5개

        // before
        System.out.println("before :" + userRepository.findByCreatedAtBefore(LocalDateTime.now().minusDays(1L))); // 어제 이전의 값 => 0개

        // 값 비교
        // GreaterThan LessThan -> before after 와 차이점 : equal 지원여부
        System.out.println("greaterthan :" + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));

        System.out.println("id greaterthan :" + userRepository.findByIdGreaterThan(4L)); // 기대값 5

        System.out.println("id :" + userRepository.findByIdGreaterThanEqual(4L)); // 기대값 4,5

        System.out.println("id :" + userRepository.findByIdLessThan(4L)); // 기대값 1,2,3

        //between
        System.out.println("between :" + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L))); // 어제와 내일 사이 -> 오늘 -> 5개

        System.out.println("id between :" + userRepository.findByIdBetween(1L, 3L)); // 1,3 사이 -> 2 => 1,2,3 이 나옴. -> between 은 양 끝값을 포함.

        // 빈값
        System.out.println("is not null :" + userRepository.findByIdIsNotNull());

//        System.out.println("is not empty :" + userRepository.findByAddressesIsNotEmpty());

        // 인절
        System.out.println("find by name in :" + userRepository.findByNameIn(Lists.newArrayList("채명정","mj","jack")));


        // like
        System.out.println("starting with :" + userRepository.findByNameStartingWith("채"));

        System.out.println("ending with :" + userRepository.findByNameEndingWith("정"));

        System.out.println("contains :" + userRepository.findByNameContains("명정"));

        System.out.println("like : " + userRepository.findByNameLike("%명%")); // 양방향 %는 contains와 동일 앞에 %는 startingwith 뒤는 endingwith
    }

    @Test
    void sorting(){
        System.out.println("top1 by name :" + userRepository.findTop1ByName("채명정")); // 앞에서 첫번쨰 & 숫자1은 생략 가능.

        System.out.println("last1 by name :" + userRepository.findLast1ByName("채명정")); // 의도한 결과값이 아닌 findbyname이 동작

        System.out.println("desc :" + userRepository.findTop1ByNameOrderByIdDesc("채명정")); // 역순

        System.out.println("top2 by name :" + userRepository.findTop2ByName("채명정")); // 쿼리 리미트값에 2가 들어감. -> 순서대로 2개가 출력됨.

        System.out.println("id desc email asc :" + userRepository.findFirstByNameOrderByIdDescEmailAsc("채명정")); // name 처럼 같은 값이 있는 경우 두번쨰 지정한 조건으로  추가 정렬을 통해서 값을 가져와야함.

        System.out.println("sort1:" + userRepository.findFirstByName("채명정", Sort.by(Sort.Order.desc("id")))); // id를 기준으로 desc

        System.out.println("sort2 :" + userRepository.findFirstByName("채명정", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email")))); // id 역순 메일 정순 메서드와 동

        System.out.println("가독성 :" + userRepository.findFirstByName("채명정", getSort()));
    }

    private Sort getSort(){
        return Sort.by(Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updatedAt"));
    }

    @Test
    void pagingAndSorting(){
        System.out.println("paging :" + userRepository.findByName("채명정", PageRequest.of(0,2,getSort())).getTotalPages()); // 첫번째 페이지 / 1페이지에 하나 / 정렬
    }

    @Test
    void insertAndUpdate(){
        User user = new User();
        // insert
        user.setName("채명정");
        user.setEmail("jack2718@hanmail.net");
        userRepository.save(user);

        // update
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("채mj");
        userRepository.save(user2);


    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRowRecord().get("gender"));
    }

    @Test
    void listener(){
        User user = new User();
        user.setEmail("newemail1@naver.com");
        user.setName("jack");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        user2.setName("채채명정");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("jack27182@naver.com");
        user.setName("채명정");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        System.out.println(userRepository.findByEmail("jack27182@naver.com"));
    }

    @Test
    void preUpdateTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is :" + user);

        user.setName("jack27182");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest(){
        User user = new User();

        user.setEmail("jack2718-new@naver.com");
        user.setName("new-mj");

        userRepository.save(user);

        user.setName("new-new-mj");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

    }

    @Test
    void relationTest(){
        User user = new User();

        user.setName("david");
        user.setEmail("david@naver.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@naver.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(userRepository.findByEmail("daniel@naver.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@naver.com").getUserHistories();

        result.forEach(System.out::println);

        System.out.println("userHistory.getUser(): " + userHistoryRepository.findAll().get(0).getUser());
    }




}