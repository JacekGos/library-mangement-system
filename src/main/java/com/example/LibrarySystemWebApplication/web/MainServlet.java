package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryElementDao;
import com.example.LibrarySystemWebApplication.model.LibraryElement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "mainServlet", value = "/")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String option = request.getServletPath();
        String action = "default";
        RequestDispatcher requestDispatcher;

        switch (option) {
            case "/libraryElementList":
                action = "showList";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/search":
                action = "search";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/login":
                doPost(request, response);
                break;
            case "/edit":
                action = "edit";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            default:

                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String option = request.getServletPath();
        String action = "default";
        RequestDispatcher requestDispatcher;

        switch (option) {
            case "/login":
                requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
                break;
            default:
                break;
        }

    }

    //TODO create edit and remove option for LibraryWorker
    //TODO add register form for new users
    //TODO add account options for LibraryWorker
}
