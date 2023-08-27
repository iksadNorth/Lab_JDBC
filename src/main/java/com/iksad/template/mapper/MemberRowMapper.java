package com.iksad.template.mapper;

import com.iksad.template.entity.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member entity = new Member();
        entity.setId(rs.getLong(Member.COL_ID));
        entity.setName(rs.getString(Member.COL_NAME));
        return entity;
    }
}
