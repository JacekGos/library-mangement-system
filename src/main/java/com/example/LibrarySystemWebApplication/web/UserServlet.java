package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryElementDao;
import com.example.LibrarySystemWebApplication.dao.LibraryUserDao;
import com.example.LibrarySystemWebApplication.model.LibraryElement;
import com.example.LibrarySystemWebApplication.model.LibraryUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "libraryUserServlet", value = "/libraryUser")
public class UserServlet extends HttpServlet {

    private LibraryUserDao libraryUserDao = new LibraryUserDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "showUserList":
                libraryUserList(request, response);
                break;
            case "searchUser":
                searchedResultsLibraryUsers(request, response);
                break;
            case "deleteUser":
                doPost(request, response);
                break;
            case "userInfo":
                userInfoList(request, response);
                break;
            default:

                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "deleteUser":
//                response.sendRedirect("test.jsp");
                deleteLibraryUser(request, response);
                break;
            default:
                break;
        }

    }

    private void libraryUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();
        libraryUserList = libraryUserDao.getAllLibraryUsers();
        request.setAttribute("libraryUserList", libraryUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userList.jsp");
        requestDispatcher.forward(request, response);

    }

    private void searchedResultsLibraryUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("searchedUserId"));
        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();
        libraryUserList = libraryUserDao.getLibraryUsersById(userId);

        request.setAttribute("libraryUserList", libraryUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userList.jsp");
        requestDispatcher.forward(request, response);

    }

    private void deleteLibraryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));

        libraryUserDao.deleteLibraryUser(libraryUserId);
        libraryUserList(request, response);
    }

    private void userInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();
        libraryUserList = libraryUserDao.getAllLibraryUsers();
        request.setAttribute("libraryUserList", libraryUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userList.jsp");
        requestDispatcher.forward(request, response);

    }
}
