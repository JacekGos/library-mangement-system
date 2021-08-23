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
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet(name = "mainServlet", value = "/")
public class MainServlet extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String option = request.getServletPath();

        switch (option) {
            //LibraryElementServlet
            case "/libraryElementList":
                doRedirect(request, response ,"showList", "libraryElement");
                break;
            case "/search":
                doRedirect(request, response ,"search", "libraryElement");
                break;
            case "/editLibraryElement":
                doRedirect(request, response ,"editLibraryElement", "libraryElement");
                break;
            case "/newLibraryElement":
                doRedirect(request, response ,"newLibraryElement", "libraryElement");
                break;
            case "/delete":
                doPost(request, response);
                break;
            //UserServlet
            case "/userList":
                doRedirect(request, response ,"showUserList", "libraryUser");
                break;
            case "/searchUser":
                doRedirect(request, response ,"searchUser", "libraryUser");
                break;
            case "/deleteUser":
                doPost(request, response);
                break;
            case "/newUser":
                doRedirect(request, response ,"newUser", "libraryUser");
                break;
            case "/userInfo":
                doRedirect(request, response ,"userInfo", "libraryUser");
                break;
            case "/userInfoAfterEndBorrowing":
                doRedirect(request, response ,"userInfoAfterEndBorrowing", "libraryUser");
                break;
            case "/userPenalty":
                doRedirect(request, response ,"userPenalty", "libraryUser");
                break;
            case "/regulatePenalty":
                doPost(request, response);
                break;
            //BorrowingProcessServlet
            case "/requestList":
                doRedirect(request, response ,"showRequests", "borrowingProcess");
                break;
            case "/searchRequest":
                doRedirect(request, response ,"searchRequest", "borrowingProcess");
                break;
            case "/requestApprove":
                doRedirect(request, response ,"requestApprove", "borrowingProcess");
                break;
            case "/borrowingList":
                doRedirect(request, response ,"showBorrowings", "borrowingProcess");
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

        switch (option) {
            //LibraryElementServlet
            case "/update":
                doRedirect(request, response, "update", "libraryElement");
                break;
            case "/delete":
                doRedirect(request, response, "delete", "libraryElement");
                break;
            case "/insert":
                doRedirect(request, response, "insert", "libraryElement");
                break;
            //UserServlet
            case "/deleteUser":
                doRedirect(request, response, "deleteUser", "libraryUser");
                break;
            case "/insertUser":
                doRedirect(request, response, "insertUser", "libraryUser");
                break;
            case "/regulatePenalty":
                doRedirect(request, response, "regulatePenalty", "libraryUser");
                break;
            //BorrowingProcessServlet
            case "/borrowLibraryElement":
                doRedirect(request, response, "borrowLibraryElement", "borrowingProcess");
                break;
            case "/rejectRequest":
                doRedirect(request, response, "rejectRequest", "borrowingProcess");
                break;
            case "/acceptRequest":
                doRedirect(request, response, "acceptRequest", "borrowingProcess");
                break;
            case "/endBorrowing":
                doRedirect(request, response ,"endBorrowing", "borrowingProcess");
                break;
            //LoginProcessServlet
            case "/login":
                doRedirect(request, response ,null, "account/login.jsp");
                break;
            default:
                break;
        }
    }

    private void doRedirect(HttpServletRequest request, HttpServletResponse response,
                            String action, String servletName) throws ServletException, IOException {

        if (action == null) {
            requestDispatcher = request.getRequestDispatcher(servletName);
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("action", action);
            requestDispatcher = request.getRequestDispatcher(servletName);
            requestDispatcher.forward(request, response);
        }
    }

}
