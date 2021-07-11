package com.example.LibrarySystemWebApplication.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginProcess", value = "/loginProcess")
public class LoginProcessServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("login");
        String password= request.getParameter("password");

        boolean status = LoginDao.validate(name, password);

        if (status == true) {
            response.sendRedirect("Logged.jsp");


        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
