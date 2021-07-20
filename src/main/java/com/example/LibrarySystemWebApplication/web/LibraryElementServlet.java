package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryElementDao;
import com.example.LibrarySystemWebApplication.model.Book;
import com.example.LibrarySystemWebApplication.model.LibraryElement;
import com.example.LibrarySystemWebApplication.model.Movie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "libraryElementServlet", value = "/libraryElement")
public class LibraryElementServlet extends HttpServlet {

    private LibraryElementDao libraryElementDao = new LibraryElementDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "showList":
                libraryElementList(request, response);
                break;
            case "search":
                searchedResultsLibraryElements(request, response);
                break;
            case "edit":
                showEditFrom(request, response);
                break;
            case "delete":
                doPost(request, response);
                break;
            case "new":
                addLibraryElement(request, response);
                break;
            default:

                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "update":
                updateLibraryElement(request, response);
                break;
            case "delete":
                deleteLibraryElement(request, response);
                break;
            case "insert":
                insertLibraryElement(request, response);
                break;
            default:
                break;
        }

    }

    private void libraryElementList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<LibraryElement> libraryElementsList = new ArrayList<LibraryElement>();
        libraryElementsList = libraryElementDao.getAllLibraryElements();
        request.setAttribute("libraryElementList", libraryElementsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("searcher.jsp");
        requestDispatcher.forward(request, response);

    }

    private void searchedResultsLibraryElements(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("searchedTitle");
        List<LibraryElement> libraryElementList = libraryElementDao.getLibraryElementsByTitle(title);
        request.setAttribute("libraryElementList", libraryElementList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("searcher.jsp");
        requestDispatcher.forward(request, response);

    }

    private void showEditFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryElementId = Integer.parseInt(request.getParameter("libraryElementId"));
        LibraryElement libraryElement = libraryElementDao.getLibraryElementById(libraryElementId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("libraryElementForm.jsp");
        request.setAttribute("libraryElement", libraryElement);
        requestDispatcher.forward(request, response);

    }

    private void updateLibraryElement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LibraryElement libraryElement = null;
        int libraryElementId = Integer.parseInt(request.getParameter("libraryElementId"));
        String title = request.getParameter("title");
        int sortId = Integer.parseInt(request.getParameter("sortId"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        byte typeId = Byte.parseByte(request.getParameter("typeId"));
        if (typeId == 1) {
            int pagesNumber = Integer.parseInt(request.getParameter("pagesNumber"));
            libraryElement = new Book(libraryElementId, typeId, title, sortId, statusId, pagesNumber);
        } else if (typeId == 2) {
            int durationTime = Integer.parseInt(request.getParameter("durationTime"));
            libraryElement = new Movie(libraryElementId, typeId, title, sortId, statusId, durationTime);
        }

        LibraryElementDao.updateLibraryElement(libraryElement);
        libraryElementList(request, response);

    }

    private void deleteLibraryElement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryElementId = Integer.parseInt(request.getParameter("libraryElementId"));

        LibraryElementDao.deleteLibraryElement(libraryElementId);
        libraryElementList(request, response);

    }

    private void addLibraryElement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("libraryElementForm.jsp");
        requestDispatcher.forward(request, response);

    }

    private void insertLibraryElement(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {

        LibraryElement libraryElement = null;
        String title = request.getParameter("title");
        byte typeId = Byte.parseByte(request.getParameter("typeId"));
        int sortId = Integer.parseInt(request.getParameter("sortId"));

        if (typeId == 1) {
            int pagesNumber = Integer.parseInt(request.getParameter("detailedInfo"));
            libraryElement = new Book(0, typeId, title, sortId, 1, pagesNumber);
        } else if (typeId == 2) {
            int durationTime = Integer.parseInt(request.getParameter("detailedInfo"));
            libraryElement = new Movie(0, typeId, title, sortId, 1, durationTime);
        }

        libraryElementDao.insertLibraryElement(libraryElement);
        response.sendRedirect("index.jsp");
    }

}

   /* PrintWriter out = response.getWriter();
                out.println("action");*/