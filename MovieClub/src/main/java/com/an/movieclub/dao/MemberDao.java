/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.movieclub.dao;

import com.an.movieclub.model.Member;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author anugent
 */
public interface MemberDao {

    public void addMember(Member member);

    public void updateMember(Member member);

    public void deleteMember(int member_id);

    public Member getMemberById(int member_id);

    public List<Member> getAllMembers();

    public Member getMemberForEvent(int event_id);

    public void saveImage(InputStream fileContent, Member member);

}
