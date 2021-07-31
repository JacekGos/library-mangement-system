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
            //LibraryElementServlet
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
            case "/editLibraryElement":
                action = "editLibraryElement";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/newLibraryElement":
                action = "newLibraryElement";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryElement");
                requestDispatcher.forward(request, response);
                break;
            case "/delete":
                doPost(request, response);
                break;
            //UserServlet
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
            case "/userInfo":
                action = "userInfo";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/userInfoAfterEndBorrowing":
                action = "userInfoAfterEndBorrowing";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/userPenalty":
                action = "userPenalty";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            case "/regulatePenalty":
                doPost(request, response);
                break;
            //BorrowingProcessServlet
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
            case "/borrowingList":
                action = "showBorrowings";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            case "/borrowLibraryElement":
                doPost(request, response);
                break;
            case "/endBorrowing":
                doPost(request, response);
                break;
            //LoginProcessServlet
            case "/login":
                doPost(request, response);
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
            //LibraryElementServlet
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
            //UserServlet
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
            case "/regulatePenalty":
                action = "regulatePenalty";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("libraryUser");
                requestDispatcher.forward(request, response);
                break;
            //BorrowingProcessServlet
            case "/borrowLibraryElement":
                action = "borrowLibraryElement";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            case "/rejectRequest":
                action = "rejectRequest";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            case "/acceptRequest":
                action = "acceptRequest";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            case "/endBorrowing":
                action = "endBorrowing";
                request.setAttribute("action", action);
                requestDispatcher = request.getRequestDispatcher("borrowingProcess");
                requestDispatcher.forward(request, response);
                break;
            //LoginProcessServlet
            case "/login":
                requestDispatcher = request.getRequestDispatcher("account/login.jsp");
                requestDispatcher.forward(request, response);
                break;
            default:
                break;
        }

    }

}
