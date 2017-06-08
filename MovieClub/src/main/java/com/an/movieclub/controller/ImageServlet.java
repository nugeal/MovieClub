package com.an.movieclub.controller;

import com.an.movieclub.model.Member;
import com.an.movieclub.service.MovieClubServiceLayer;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author anugent
 */
@WebServlet(urlPatterns = {"/photos/*"})
public class ImageServlet extends HttpServlet {

    private MovieClubServiceLayer service;

    ApplicationContext ctx
                = new ClassPathXmlApplicationContext("spring-persistence.xml");

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        service = ctx.getBean("service", MovieClubServiceLayer.class);

        //Gets the hero_id value from the request URL
        String member_idString = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        int member_id = Integer.parseInt(member_idString);
        Member member = service.getMemberById(member_id);
        byte[] imageContent = member.getMember_image();
        response.getOutputStream().write(imageContent);

    }



}
