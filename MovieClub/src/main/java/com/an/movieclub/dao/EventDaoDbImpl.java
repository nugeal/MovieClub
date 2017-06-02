/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.dao;

import com.an.movieclub.model.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anugent
 */
public class EventDaoDbImpl implements EventDao {

    private JdbcTemplate jdbcTemplate;
    private MemberDao memberDao;

    @Inject
    public EventDaoDbImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // use setter injection to inject the jdbcTemplate object defined in the
    // spring-persistence xml file
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //SQL Prepared Statements
    private static final String SQL_INSERT_EVENT
            = "insert into event (member_id, movie_name, event_date, theme, location) "
            + "values (?,?,?,?,?)";

    private static final String SQL_UPDATE_EVENT
            = "update event set member_id = ?, movie_name = ?, event_date = ?, "
            + "theme = ?, location = ? where event_id = ?";

    private static final String SQL_DELETE_EVENT
            = "delete from event where event_id = ?";

    private static final String SQL_SELECT_EVENT
            = "select * from event where event_id = ?";

    private static final String SQL_SELECT_ALL_EVENTS
            = "select * from event";

    private static final String SQL_SELECT_EVENTS_BY_DATE
            = "select * from event where event_date between ? and ?";

    private static final String SQL_SELECT_EVENTS_BY_MEMBER
            = "select * from event where member_id = ?";

    private static final String SQL_SELECT_EVENTS_BY_MOVIE
            = "select * from event where movie_name = ?";

    private static final String SQL_SELECT_FUTURE_EVENTS
            = "select * from event where `event_date` > ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addEvent(Event event) {
        jdbcTemplate.update(SQL_INSERT_EVENT, event.getMember().getMember_id(),
                event.getMovie_name(),
                event.getEvent_date().toString(),
                event.getTheme(),
                event.getLocation());

        int event_id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        event.setEvent_id(event_id);
    }

    @Override
    public void updateEvent(Event event) {
        jdbcTemplate.update(SQL_UPDATE_EVENT, event.getMember().getMember_id(),
                event.getMovie_name(),
                event.getEvent_date().toString(),
                event.getTheme(),
                event.getLocation(),
                event.getEvent_id());
    }

    @Override
    public void deleteEvent(int event_id) {
        jdbcTemplate.update(SQL_DELETE_EVENT, event_id);
    }

    @Override
    public Event getEventById(int event_id) {
        try {
            Event event = jdbcTemplate.queryForObject(SQL_SELECT_EVENT,
                    new EventMapper(), event_id);

            // set member field of the Event object
            event.setMember(memberDao.getMemberForEvent(event_id));
            return event;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> eventList = jdbcTemplate.query(SQL_SELECT_ALL_EVENTS,
                new EventMapper());

        for (Event currentEvent : eventList) {
            currentEvent.setMember(memberDao.getMemberForEvent(currentEvent.getEvent_id()));
        }
        return eventList;
    }

    @Override
    public List<Event> getEventsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Event> eventList = jdbcTemplate.query(SQL_SELECT_EVENTS_BY_DATE,
                new EventMapper(), startDate.toString(), endDate.toString());

        for (Event currentEvent : eventList) {
            currentEvent.setMember(memberDao.getMemberForEvent(currentEvent.getEvent_id()));
        }
        return eventList;
    }

    @Override
    public List<Event> getEventsByMemberId(int member_id) {
        List<Event> eventList = jdbcTemplate.query(SQL_SELECT_EVENTS_BY_MEMBER,
                new EventMapper(), member_id);

        //set the member field for each Event object
        for (Event currentEvent : eventList) {
            currentEvent.setMember(memberDao.getMemberForEvent(currentEvent.getEvent_id()));
        }
        return eventList;
    }

    @Override
    public List<Event> getEventsByMovieName(String movie_name) {
        List<Event> eventList = jdbcTemplate.query(SQL_SELECT_EVENTS_BY_MOVIE,
                new EventMapper(), movie_name);

        for (Event currentEvent : eventList) {
            currentEvent.setMember(memberDao.getMemberForEvent(currentEvent.getEvent_id()));
        }
        return eventList;
    }

    @Override
    public List<Event> getUpcomingEvents() {
        LocalDate today = LocalDate.now();
        try {
            List<Event> eventList = jdbcTemplate.query(SQL_SELECT_FUTURE_EVENTS,
                    new EventMapper(), today.toString());

            for (Event currentEvent : eventList) {
                currentEvent.setMember(memberDao.getMemberForEvent(currentEvent.getEvent_id()));
            }
            return eventList;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    // MAPPER classes
    // using Nested classes since MovieClubDaoDbImpl is the only class that will
    // call the mapRow methods
    private static final class EventMapper implements RowMapper<Event> {

        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

            Event event = new Event();
            event.setEvent_id(rs.getInt("event_id"));
            event.setMovie_name(rs.getString("movie_name"));
            event.setEvent_date(rs.getDate("event_date").toLocalDate());
            event.setTheme(rs.getString("theme"));
            event.setLocation(rs.getString("location"));
            return event;
        }
    }
}
