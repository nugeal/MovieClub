/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.dao;

import com.an.movieclub.model.Event;
import com.an.movieclub.model.Member;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author anugent
 */
public class MemberDaoTest {

    private MemberDao memberDao;
    private EventDao eventDao;

    public MemberDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        memberDao = ctx.getBean("memberDao", MemberDao.class);
        eventDao = ctx.getBean("eventDao", EventDao.class);
        // Delete all records in the event table to get the database into a
        // known state before each test
        List<Event> eventList = eventDao.getAllEvents();
        for (Event currentEvent : eventList) {
            eventDao.deleteEvent(currentEvent.getEvent_id());
        }

        // Delete all records in the member table
        List<Member> memberList = memberDao.getAllMembers();
        for (Member currentMember : memberList) {
            memberDao.deleteMember(currentMember.getMember_id());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addMember and getMemberById methods, of class MovieClubDao.
     */
    @Test
    public void testAddGetMember() {
        Member member =  new Member();
        member.setFirst_name("neo");
        member.setLast_name("matrix");
        memberDao.addMember(member);
        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(fromDao, member);
    }

    /**
     * Test of updateMember method, of class MovieClubDao.
     */
    @Test
    public void testUpdateMember() {
        Member member =  new Member();
        member.setFirst_name("neo");
        member.setLast_name("matrix");
        memberDao.addMember(member);
        member.setFirst_name("neoander");
        memberDao.updateMember(member);
        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(fromDao, member);
    }

    /**
     * Test of deleteMember method, of class MovieClubDao.
     */
    @Test
    public void testDeleteMember() {
        Member member =  new Member();
        member.setFirst_name("neo");
        member.setLast_name("matrix");
        memberDao.addMember(member);
        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(fromDao, member);
        memberDao.deleteMember(member.getMember_id());
        assertNull(memberDao.getMemberById(member.getMember_id()));
    }

    /**
     * Test of getAllMembers method, of class MovieClubDao.
     */
    @Test
    public void testGetAllMembers() {
        List<Member> expectedMembers = new ArrayList<>();

        Member member =  new Member();
        member.setFirst_name("neo");
        member.setLast_name("matrix");
        memberDao.addMember(member);
        expectedMembers.add(member);

        Member member2 = new Member();
        member2.setFirst_name("john");
        member2.setLast_name("doe");
        memberDao.addMember(member2);
        expectedMembers.add(member2);

        List<Member> fromDao = memberDao.getAllMembers();
        assertEquals(expectedMembers, fromDao);
    }

}
