package com.example.LibrarySystemWebApplication.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutProcess", value = "/logoutProcess")
public class LogoutProcessServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.setCharacterEncoding("UTF-8");

        System.out.println("in LogoutProcess");

        HttpSession userSession = request.getSession();
        userSession.setAttribute("userName", null);
        userSession.setAttribute("userId", null);
        userSession.setAttribute("userType", null);
        userSession.setAttribute("userLogin", null);
        userSession.invalidate();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.sendRedirect("account/login.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
