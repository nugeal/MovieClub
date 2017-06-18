/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.controller;

import com.an.movieclub.model.Event;
import com.an.movieclub.model.Member;
import com.an.movieclub.service.MovieClubServiceLayer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author anugent
 */
@Controller
public class EventController {

    private MovieClubServiceLayer service;

    @Inject
    public EventController(MovieClubServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/upcoming", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getUpcomingEvents() {
        List<Event> events = service.getUpcomingEvents();
        return events;
    }

    @RequestMapping(value = "/displayEventsPage", method = RequestMethod.GET)
    public String getEventsPage(Model model) {

         List<Event> upcomingEvents = service.getUpcomingEvents();
         model.addAttribute("upcomingEventList", upcomingEvents);

        List<Event> pastEvents = service.getPastEvents();
        model.addAttribute("pastEventList", pastEvents);

        return "eventsPage";
    }

    @RequestMapping(value = "/addEventForm", method = RequestMethod.GET)
    public String addEventForm(Model model) {

        List<Member> members = service.getAllMembers();
        model.addAttribute("memberList", members);

        return "addEventForm";
    }

    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public String addEvent(HttpServletRequest request) {
        Event event = new Event();
        String member = request.getParameter("member");
        //the replaceAll method replaces the portion of the member string after
        //the "=" with an empty string so that the member_id string portion can be
        //parsed into an int.
        String stmember_Id = member.replaceAll("[^0-9]*-.*", "");
        int member_id = Integer.parseInt(stmember_Id);

        event.setEvent_date((LocalDate.parse(request.getParameter("event_date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        event.setMember(service.getMemberById(member_id));
        event.setTheme(request.getParameter("theme"));
        event.setMovie_name(request.getParameter("movie_name"));
        event.setLocation(request.getParameter("location"));

        service.addEvent(event);

        return "redirect:displayEventsPage";
    }

    @RequestMapping(value = "/updateEventForm", method = RequestMethod.GET)
    public String displayUpdateEventForm(HttpServletRequest request, Model model) {
        String eventIdParameter = request.getParameter("event_id");
        int event_id = Integer.parseInt(eventIdParameter);
        Event event = service.getEventBy_Id(event_id);
        model.addAttribute("event", event);

        List<Member> members = service.getAllMembers();
        members.remove(event.getMember());
        model.addAttribute("memberList", members);

        return "editEventForm";
    }

    @RequestMapping(value = "/editEvent", method = RequestMethod.POST)
    public String editEvent(HttpServletRequest request) {

        String member = request.getParameter("member");
        String stMember_Id = member.replaceAll("[^0-9]*-.*", "");
        int member_id = Integer.parseInt(stMember_Id);

        String stEvent_Id = request.getParameter("event_id");
        int event_id = Integer.parseInt(stEvent_Id);
        Event event = service.getEventBy_Id(event_id);

        event.setEvent_date((LocalDate.parse(request.getParameter("event_date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        event.setMember(service.getMemberById(member_id));
        event.setTheme(request.getParameter("theme"));
        event.setMovie_name(request.getParameter("movie_name"));
        event.setLocation(request.getParameter("location"));

        service.updateEvent(event);

        return "redirect:displayEventsPage";
    }

    @RequestMapping(value = "/searchEventsPage", method = RequestMethod.GET)
    public String getSearchEventsPage(Model model) {

        List<Member> members = service.getAllMembers();
        model.addAttribute("memberList", members);

        return "searchEventsPage";
    }

    @RequestMapping(value="/searchByMember/{member}", method=RequestMethod.GET)
    @ResponseBody
    public List<Event> searchByMember(@PathVariable("member") int member_id) {

        return service.getEventsByMember_Id(member_id);
    }

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
    public String deleteEvent(HttpServletRequest request, Model model) {
        String eventIdParameter = request.getParameter("event_id");
        int event_id = Integer.parseInt(eventIdParameter);
        service.deleteEvent(event_id);

        return "redirect:displayEventsPage";
    }

    @RequestMapping(value="/searchByDateRange", method=RequestMethod.POST)
    @ResponseBody
    public List<Event> searchByDateRange(@RequestBody Map<String, String> dateMap) {

        LocalDate startDate = LocalDate.parse(dateMap.get("startDate"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate endDate = LocalDate.parse(dateMap.get("endDate"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return service.getEventsByDateRange(startDate, endDate);
    }

}
