/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.dao;

import com.an.movieclub.model.Event;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author anugent
 */
public interface EventDao {

    public void addEvent(Event event);

    public void updateEvent(Event event);

    public void deleteEvent(int event_id);

    public Event getEventById(int event_id);

    public List<Event> getAllEvents();

    public List<Event> getEventsByDateRange(LocalDate startDate, LocalDate endDate);

    public List<Event> getEventsByMemberId(int member_id);

    public List<Event> getEventsByMovieName(String movie_name);

    public List<Event> getUpcomingEvents();
}
