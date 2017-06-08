/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.model;

import java.util.Arrays;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author anugent
 */
public class Member {

    private int member_id;
    @NotEmpty(message = "You must supply a value for First Name.")
    @Length(max = 20, message = "Name must be no more than 20 characters in length.")
    private String first_name;
    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 20, message = "Name must be no more than 20 characters in length.")
    private String last_name;
    private byte[] member_image;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public byte[] getMember_image() {
        return member_image;
    }

    public void setMember_image(byte[] member_image) {
        this.member_image = member_image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.member_id;
        hash = 73 * hash + Objects.hashCode(this.first_name);
        hash = 73 * hash + Objects.hashCode(this.last_name);
        hash = 73 * hash + Arrays.hashCode(this.member_image);
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
        final Member other = (Member) obj;
        if (this.member_id != other.member_id) {
            return false;
        }
        if (!Objects.equals(this.first_name, other.first_name)) {
            return false;
        }
        if (!Objects.equals(this.last_name, other.last_name)) {
            return false;
        }
        if (!Arrays.equals(this.member_image, other.member_image)) {
            return false;
        }
        return true;
    }



}
