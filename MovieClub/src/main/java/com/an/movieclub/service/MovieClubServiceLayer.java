/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.service;

import com.an.movieclub.model.Event;
import java.util.List;

/**
 *
 * @author anugent
 */
public interface MovieClubServiceLayer {

    public List<Event> getUpcomingEvents();

}
