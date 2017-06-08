package com.an.movieclub.controller;

import com.an.movieclub.model.Member;
import com.an.movieclub.service.MovieClubServiceLayer;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig
public class MemberController {

    private MovieClubServiceLayer service;

    @Inject
    public MemberController(MovieClubServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value="/displayMembersPage", method=RequestMethod.GET)
    public String getMembersPage(Model model) {

        List<Member> members = service.getAllMembers();
        model.addAttribute("memberList", members);

        return "membersPage";
    }

    @RequestMapping(value="/addMemberForm", method = RequestMethod.GET)
    public ModelAndView createMember() {

        return new ModelAndView("addMemberForm","member",new Member());
    }

    @RequestMapping(value="/addMember", method=RequestMethod.POST)
    public String addMember(@Valid @ModelAttribute("member") Member member,
            BindingResult result, HttpServletRequest request) throws IOException, ServletException {

        if (result.hasErrors()) {
            return "addMemberForm";
        }

        service.addMember(member);

        Part filePart = request.getPart("member_image");
        //check if an image has been uploaded, get file name and store the
        //object
        if(filePart.getSize() > 0) {
            service.saveImage(filePart, member);
        }

        return "redirect:displayMembersPage";
    }

    @RequestMapping(value="/editMemberForm", method=RequestMethod.GET)
    public String displayEditMemberForm(HttpServletRequest request, Model model) {
        String memberIdParameter = request.getParameter("member_id");
        int member_id = Integer.parseInt(memberIdParameter);
        Member member = service.getMemberById(member_id);
        model.addAttribute("member", member);

        return "editMemberForm";
    }

    @RequestMapping(value="/editMember", method=RequestMethod.POST)
    public String updateMember(@Valid @ModelAttribute("member") Member member,
            BindingResult result, HttpServletRequest request) throws IOException, ServletException {

        if (result.hasErrors()) {
            return "editMemberForm";
        }

        service.updateMember(member);

        Part filePart = request.getPart("member_image");
        //check if an image has been uploaded, get file name and store the
        //object
        if(filePart.getSize() > 0) {
            service.saveImage(filePart, member);
        }

        return "redirect:displayMembersPage";
    }

    @RequestMapping(value="/deleteMember", method=RequestMethod.GET)
    public String deleteMember(HttpServletRequest request, Model model) {
        String memberIdParameter = request.getParameter("member_id");
        int member_id = Integer.parseInt(memberIdParameter);
        service.deleteMember(member_id);

        return "redirect:displayMembersPage";
    }
}
