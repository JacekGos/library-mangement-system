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

        request.setCharacterEncoding("UTF-8");

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
            case "/delete":
                doPost(request, response);
                break;
            case "/userList":
                action = "showUserList";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/searchUser":
                action = "searchUser";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/deleteUser":
                doPost(request, response);
                break;
            case "/newUser":
                action = "newUser";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/new":
                action = "new";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/userInfo":
                action = "userInfo";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/borrow":
                doPost(request, response);
                break;
            case "/requestList":
                action = "showRequests";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            case "/searchRequest":
                action = "searchRequest";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            case "/requestApprove":
                action = "requestApprove";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            default:

                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String option = request.getServletPath();
        String action = "default";
        RequestDispatcher requestDispatcher;

        switch (option) {
            case "/login":
                requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "/update":
                action = "update";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/delete":
                action = "delete";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/insert":
                action = "insert";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/deleteUser":
                action = "deleteUser";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/insertUser":
                action = "insertUser";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/borrow":
                action = "borrow";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            default:
                break;
        }

    }

    //TODO add register form for new users
    //TODO Search LibraryElements by fragments of title

  /*  PrintWriter out = response.getWriter();
                out.println(request.getParameter("title"));*/
}
