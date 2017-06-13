/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.service;

import com.an.movieclub.dao.EventDao;
import com.an.movieclub.dao.MemberDao;
import com.an.movieclub.model.Event;
import com.an.movieclub.model.Member;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author anugent
 */
public class MovieClubServiceImpl implements MovieClubServiceLayer {

    private EventDao eventDao;
    private MemberDao memberDao;

    @Inject
    public MovieClubServiceImpl(EventDao eventDao, MemberDao memberDao) {
        this.eventDao = eventDao;
        this.memberDao = memberDao;
    }

    @Override
    public List<Event> getUpcomingEvents() {
        return eventDao.getUpcomingEvents();
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);
    }

    @Override
    public Member getMemberById(int member_id) {
        return memberDao.getMemberById(member_id);
    }

    @Override
    public void saveImage(Part filePart, Member member) throws IOException {
        InputStream fileContent = filePart.getInputStream();
        memberDao.saveImage(fileContent, member);
    }

    @Override
    public void updateMember(Member member) {
        memberDao.updateMember(member);
    }

    @Override
    public void deleteMember(int member_id) {
        memberDao.deleteMember(member_id);
    }

    @Override
    public List<Event> getPastEvents() {
        List<Event> pastEvents = eventDao.getAllEvents()
                                .stream()
                                .filter(e -> e.getEvent_date().isBefore(LocalDate.now()) ||
                                        e.getEvent_date().isEqual(LocalDate.now()))
                                .sorted(Comparator.comparing(Event::getEvent_date))
                                .collect(Collectors.toList());

        //The Comparator use above sorts the events from least recent to most recent,
        //but the desired list is ordered in the opposite way
        Collections.reverse(pastEvents);
        return pastEvents.stream().collect(Collectors.toList());
    }

    @Override
    public void addEvent(Event event) {
        eventDao.addEvent(event);
    }

    @Override
    public Event getEventBy_Id(int event_id) {
        return eventDao.getEventById(event_id);
    }

    @Override
    public void updateEvent(Event event) {
        eventDao.updateEvent(event);
    }

    @Override
    public List<Event> getEventsByMember_Id(int member_id) {
        return eventDao.getEventsByMemberId(member_id);
    }

    @Override
    public void deleteEvent(int event_id) {
        eventDao.deleteEvent(event_id);
    }

}
