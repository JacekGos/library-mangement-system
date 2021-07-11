package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryWorkerDao;
import com.example.LibrarySystemWebApplication.dao.LoginDao;
import com.example.LibrarySystemWebApplication.model.LibraryWorker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginProcess", value = "/loginProcess")
public class LoginProcessServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("login");
        String password= request.getParameter("password");

        boolean status = false;

        status = LoginDao.validateWorker(login, password);

        if (status == true) {
            LibraryWorker worker = LibraryWorkerDao.getLibraryWorkerByLoginAndPasword(login, password);
            response.sendRedirect("test.jsp");
        } else {
            status = LoginDao.validateUser(login, password);
            if (status == true) {
                response.sendRedirect("dobrze");
            } else {
                response.sendRedirect("zle");
            }
        }
    }
}
