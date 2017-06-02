/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.dao;

import com.an.movieclub.model.Member;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anugent
 */
public class MemberDaoDbImpl implements MemberDao {

    private JdbcTemplate jdbcTemplate;

    // use setter injection to inject the jdbcTemplate object defined in the
    // spring-persistence xml file
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SQL prepared statements
    private static final String SQL_INSERT_MEMBER
            = "insert into member (first_name, last_name) "
            + "values (?,?)";

    private static final String SQL_UPDATE_MEMBER
            = "update member set first_name = ?, last_name = ? "
            + "where member_id = ?";

    private static final String SQL_INSERT_IMAGE
            = "update member set member_image = ? where member_id = ?";

    private static final String SQL_DELETE_MEMBER
            = "delete from member where member_id = ?";

    private static final String SQL_SELECT_MEMBER
            = "select * from member where member_id = ?";

    private static final String SQL_SELECT_ALL_MEMBERS
            = "select * from member";

    private static final String SQL_SELECT_MEMBER_FOR_EVENT
            = "select m.member_id, m.first_name, m.last_name, m.member_image "
            + "from member m inner join event e on m.member_id = e.member_id "
            + "where event_id = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMember(Member member) {
        jdbcTemplate.update(SQL_INSERT_MEMBER, member.getFirst_name(),
                member.getLast_name());

        // get the value of memebr_id that was created by the database
        int member_id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        member.setMember_id(member_id);
    }

    @Override
    public void updateMember(Member member) {
        jdbcTemplate.update(SQL_UPDATE_MEMBER, member.getFirst_name(),
                member.getLast_name(),member.getMember_id());
    }

    @Override
    public void deleteMember(int member_id) {
        jdbcTemplate.update(SQL_DELETE_MEMBER, member_id);
    }

    @Override
    public Member getMemberById(int member_id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MEMBER,
                    new MemberMapper(), member_id);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Member> getAllMembers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_MEMBERS, new MemberMapper());
    }

    @Override
    public Member getMemberForEvent(int event_id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_MEMBER_FOR_EVENT,
                new MemberMapper(), event_id);

    }

    @Override
    public void saveImage(InputStream fileContent, Member member) {
        jdbcTemplate.update(SQL_INSERT_IMAGE, fileContent, member.getMember_id());
    }

    // MAPPER classes
    // using Nested classes since MovieClubDaoDbImpl is the only class that will
    // call the mapRow methods
    private static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

            Member member = new Member();
            member.setMember_id(rs.getInt("member_id"));
            member.setFirst_name(rs.getString("first_name"));
            member.setLast_name(rs.getString("last_name"));
            member.setMember_image(rs.getBytes("member_image"));
            return member;
        }
    }

}
