package com.an.movieclub.controller;

import com.an.movieclub.model.Event;
import com.an.movieclub.service.MovieClubServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieClubController {

    private MovieClubServiceLayer service;

    @Inject
    public MovieClubController(MovieClubServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value="/upcoming", method=RequestMethod.GET)
    @ResponseBody
    public List<Event> getUpcomingEvents() {
        List<Event> events = service.getUpcomingEvents();
        return events;
    }
}
