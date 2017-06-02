/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author anugent
 */
public class Event {

    private int event_id;
    private Member member;
    private String movie_name;
    private LocalDate event_date;
    private String theme;
    private String location;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.event_id;
        hash = 89 * hash + Objects.hashCode(this.member);
        hash = 89 * hash + Objects.hashCode(this.movie_name);
        hash = 89 * hash + Objects.hashCode(this.event_date);
        hash = 89 * hash + Objects.hashCode(this.theme);
        hash = 89 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.event_id != other.event_id) {
            return false;
        }
        if (!Objects.equals(this.movie_name, other.movie_name)) {
            return false;
        }
        if (!Objects.equals(this.theme, other.theme)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        if (!Objects.equals(this.event_date, other.event_date)) {
            return false;
        }
        return true;
    }



}
