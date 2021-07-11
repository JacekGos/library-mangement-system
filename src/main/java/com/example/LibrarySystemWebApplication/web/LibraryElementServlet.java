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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "libraryElementServlet", value = "/libraryElement")
public class LibraryElementServlet extends HttpServlet {

    private LibraryElementDao libraryElementDao = new LibraryElementDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = (String)request.getAttribute("action");

    /*    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(action);
        out.println("</body></html>");*/

        switch (action) {
            case "ShowList":
                libraryElementList(request, response);
            case "/search":
//                searchedResultsLibraryElements(request, response);
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
        List<LibraryElement> searchedLibraryElements = libraryElementDao.getLibraryElementsByTitle(title);
        request.setAttribute("searchedLibraryElements", searchedLibraryElements);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("searcher.jsp");
        requestDispatcher.forward(request, response);
    }

}
