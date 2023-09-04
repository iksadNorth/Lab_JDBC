package com.iksad.template.repository;

import com.iksad.template.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member entity);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    void deleteById(Long id) throws IllegalArgumentException;
}
