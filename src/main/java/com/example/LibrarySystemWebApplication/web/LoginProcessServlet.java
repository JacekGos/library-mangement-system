package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryUserDao;
import com.example.LibrarySystemWebApplication.dao.LibraryWorkerDao;
import com.example.LibrarySystemWebApplication.dao.LoginDao;
import com.example.LibrarySystemWebApplication.model.LibraryUser;
import com.example.LibrarySystemWebApplication.model.LibraryWorker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginProcess", value = "/loginProcess")
public class LoginProcessServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password= request.getParameter("password");

        boolean status = false;
        HttpSession userSession;

        status = LoginDao.validateWorker(login, password);

        if (status == true) {
            LibraryWorker worker = LibraryWorkerDao.getLibraryWorkerByLoginAndPasword(login, password);

            userSession = request.getSession();
            userSession.setAttribute("userName", worker.getUserName());
            userSession.setAttribute("userType", worker.getAccountType());
            userSession.setAttribute("userLogin", worker.getLogin());
            response.sendRedirect("index.jsp");

        } else {
            status = LoginDao.validateUser(login, password);
            if (status == true) {

                LibraryUser user = LibraryUserDao.getLibraryUserByLoginAndPasword(login, password);

                userSession = request.getSession();
                userSession.setAttribute("userId", user.getUserId());
                userSession.setAttribute("userName", user.getUserName());
                userSession.setAttribute("userType", user.getAccountType());
                userSession.setAttribute("userLogin", user.getLogin());
                response.sendRedirect("index.jsp");

            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
}
