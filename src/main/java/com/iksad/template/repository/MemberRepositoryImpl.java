package com.iksad.template.repository;

import com.iksad.template.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@NoArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Member save(Member entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Optional<Member> findById(Long id) {
        try {
            Member entity = em.find(Member.class, id);
            return Optional.of(entity);
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = String.format("SELECT m FROM %s m", Member.TABLE);
        TypedQuery<Member> query = em.createQuery(sql, Member.class);

        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) throws IllegalArgumentException {
        Member entity = findById(id).orElseThrow(IllegalArgumentException::new);
        em.remove(entity);
    }
}
