package com.iksad.template.repository;

import com.iksad.template.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest @Transactional
class MemberRepositoryImplTest {
    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void printStartLine() {
        System.out.println("=".repeat(50));
    }

    @Test
    void save() {
        // given
        Member entity = new Member();
        entity.setName("iksadWest");

        // when
        Member saved = repository.save(entity);

        // then
        System.out.println(saved);
    }

    @Test
    void findById() {
        // given
        long id = 1L;

        // when
        Optional<Member> optionalMember = repository.findById(id);

        // then
        optionalMember.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("검색 결과 없음.")
        );
    }

    @Test
    void findAll() {
        // given

        // when
        List<Member> members = repository.findAll();

        // then
        members.forEach(System.out::println);
    }

    @Test
    void deleteById() {
        // given
        Member entity = new Member();
        entity.setName("iksadEast");
        Member saved = repository.save(entity);
        Long id = saved.getId();
        System.out.printf("ID saved : %s \n", id);

        // when
        repository.deleteById(id);

        // then
        repository.findById(id).ifPresentOrElse(
                member -> System.out.println("아직 삭제 안됨."),
                () -> System.out.println("정상적으로 삭제됨.")
        );
    }
}