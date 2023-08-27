package com.iksad.template.repository;

import com.iksad.template.entity.Member;
import com.iksad.template.mapper.MemberRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public Member save(Member entity) {
        boolean isNewEntity = (entity.getId() == null);
        Long id = isNewEntity ? create(entity) : update(entity);
        return findById(id).orElse(null);
    }

    private Long create(Member entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = String.format("INSERT INTO %s (name) VALUES (?)", Member.TABLE);
        PreparedStatementCreator psc = conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            return ps;
        };
        jdbcTemplate.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private Long update(Member entity) {
        String sql = String.format("UPDATE %s SET name = ? WHERE id = ?", Member.TABLE);
        jdbcTemplate.update(sql, entity.getName(), entity.getId());
        return  entity.getId();
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", Member.TABLE);
        Member entity;
        try {
            entity = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public List<Member> findAll() {
        String sql = String.format("SELECT * FROM %s", Member.TABLE);
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    @Override
    public void deleteById(Long id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", Member.TABLE);
        jdbcTemplate.update(sql, id);
    }
}
