/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.dao;

import com.an.movieclub.model.Event;
import com.an.movieclub.model.Member;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class EventDaoTest {

    private MemberDao memberDao;
    private EventDao eventDao;

    public EventDaoTest() {
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
        eventDao = ctx.getBean("eventDao", EventDao.class);
        memberDao = ctx.getBean("memberDao", MemberDao.class);

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
     * Test of addEvent and getEventById methods, of class MovieClubDao.
     */
    @Test
    public void testAddGetEvent() {
        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("summer");
        event.setLocation("Neo's House");
        eventDao.addEvent(event);

        Event fromDao = eventDao.getEventById(event.getEvent_id());
        assertEquals(fromDao, event);
    }

    /**
     * Test of updateEvent method, of class MovieClubDao.
     */
    @Test
    public void testUpdateEvent() {
        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("summer");
        event.setLocation("Neo's House");
        eventDao.addEvent(event);

        // update the location
        event.setLocation("John's house");
        eventDao.updateEvent(event);
        Event fromDao = eventDao.getEventById(event.getEvent_id());
        assertEquals(fromDao, event);
    }

    /**
     * Test of deleteEvent method, of class MovieClubDao.
     */
    @Test
    public void testDeleteEvent() {
        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("summer");
        event.setLocation("Neo's House");
        eventDao.addEvent(event);

        Event fromDao = eventDao.getEventById(event.getEvent_id());
        assertEquals(fromDao, event);
        eventDao.deleteEvent(event.getEvent_id());
        assertNull(eventDao.getEventById(event.getEvent_id()));
    }


    /**
     * Test of getAllEvents method, of class MovieClubDao.
     */
    @Test
    public void testGetAllEvents() {
        List<Event> eventList = new ArrayList<>();

        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("winter");
        event.setLocation("John's House");
        eventDao.addEvent(event);
        eventList.add(event);

        Event event2 = new Event();
        event2.setMember(member);
        event2.setEvent_date(LocalDate.parse("07/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event2.setTheme("summer");
        event2.setLocation("Neo's House");
        eventDao.addEvent(event2);
        eventList.add(event2);

        assertEquals(eventList, eventDao.getAllEvents());
    }

    /**
     * Test of getEventsByDateRange method, of class MovieClubDao.
     */
    @Test
    public void testGetEventsByDateRange() {

        List<Event> expectedList = new ArrayList<>();

        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("winter");
        event.setLocation("John's House");
        eventDao.addEvent(event);
        expectedList.add(event);

        Event event2 = new Event();
        event2.setMember(member);
        event2.setEvent_date(LocalDate.parse("07/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event2.setTheme("summer");
        event2.setLocation("Neo's House");
        eventDao.addEvent(event2);

        Event event3 = new Event();
        event3.setMember(member);
        event3.setEvent_date(LocalDate.parse("03/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event3.setTheme("spring");
        event3.setLocation("Neo's House");
        eventDao.addEvent(event3);
        expectedList.add(event3);

        assertEquals(expectedList, eventDao.getEventsByDateRange(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                            LocalDate.parse("04/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
    }

    /**
     * Test of getEventsByMemberId method, of class MovieClubDao.
     */
    @Test
    public void testGetEventsByMemberId() {
        List<Event> eventList = new ArrayList<>();

        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("winter");
        event.setLocation("John's House");
        eventDao.addEvent(event);
        eventList.add(event);

        Event event2 = new Event();
        event2.setMember(member);
        event2.setEvent_date(LocalDate.parse("07/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event2.setTheme("summer");
        event2.setLocation("Neo's House");
        eventDao.addEvent(event2);
        eventList.add(event2);

        Event event3 = new Event();
        event3.setMember(member);
        event3.setEvent_date(LocalDate.parse("03/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event3.setTheme("spring");
        event3.setLocation("Neo's House");
        eventDao.addEvent(event3);
        eventList.add(event3);

        assertEquals(eventList, eventDao.getEventsByMemberId(member.getMember_id()));
    }

    /**
     * Test of getEventsByMovieName method, of class MovieClubDao.
     */
    @Test
    public void testGetEventsByMovieName() {
        List<Event> resultList = new ArrayList<>();
        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("01/01/2013",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("winter");
        event.setLocation("John's House");
        event.setMovie_name("The Iron Lady");
        eventDao.addEvent(event);
        resultList.add(event);

        List<Event> fromDao = eventDao.getEventsByMovieName("The Iron Lady");
        assertEquals(resultList, fromDao);
    }

    /**
     * Test of getUpcomingEvents method, of class MovieClubDao.
     */
    @Test
    public void testGetUpcomingEvents() {
        List<Event> resultList = new ArrayList<>();
        Member member =  new Member();
        member.setFirst_name("john");
        member.setLast_name("doe");
        memberDao.addMember(member);

        Event event = new Event();
        event.setMember(member);
        event.setEvent_date(LocalDate.parse("12/01/2060",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        event.setTheme("winter");
        event.setLocation("John's House");
        eventDao.addEvent(event);
        resultList.add(event);

        List<Event> fromDao = eventDao.getUpcomingEvents();
        assertEquals(resultList, fromDao);
    }

    /**
     * Test of getUpcomingEvents method, of class MovieClubDao.
     */
    @Test
    public void testGetUpcomingEvents_NoEvents() {
        List<Event> resultList = new ArrayList<>();

        List<Event> fromDao = eventDao.getUpcomingEvents();
        assertEquals(resultList, fromDao);
    }

}
