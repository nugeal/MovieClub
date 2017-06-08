/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.service;

import com.an.movieclub.model.Event;
import com.an.movieclub.model.Member;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author anugent
 */
public interface MovieClubServiceLayer {

    public List<Event> getUpcomingEvents();

    public List<Member> getAllMembers();

    public void addMember(Member member);

    public Member getMemberById(int member_id);

    public void saveImage(Part filePart, Member member) throws IOException;

    public void updateMember(Member member);

    public void deleteMember(int member_id);

    public List<Event> getPastEvents();

    public void addEvent(Event event);

    public Event getEventBy_Id(int event_id);

    public void updateEvent(Event event);

    public List<Event> getEventsByMember_Id(int member_id);

}
