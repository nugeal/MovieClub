/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.service;

import com.an.movieclub.dao.EventDao;
import com.an.movieclub.dao.MemberDao;
import com.an.movieclub.model.Event;
import java.util.List;
import javax.inject.Inject;

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

}
