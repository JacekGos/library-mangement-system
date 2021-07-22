package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryElementDao;
import com.example.LibrarySystemWebApplication.dao.LibraryUserDao;
import com.example.LibrarySystemWebApplication.dao.RequestDao;
import com.example.LibrarySystemWebApplication.model.Borrowing;
import com.example.LibrarySystemWebApplication.model.LibraryElement;
import com.example.LibrarySystemWebApplication.model.LibraryUser;
import com.example.LibrarySystemWebApplication.model.Request;
import com.example.LibrarySystemWebApplication.dao.BorrowingDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "borrrowingProcess", value = "/borrowingProcess")
public class BorrowingProcessServlet extends HttpServlet {

    private LibraryElementDao libraryElementDao = new LibraryElementDao();
    private LibraryUserDao libraryUserDao = new LibraryUserDao();
    private RequestDao requestDao = new RequestDao();
    private BorrowingDao borrowingDao = new BorrowingDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "borrow":
                doPost(request, response);
                break;
            case "showRequests":
                showRequestsList(request, response);
                break;
            case "searchRequest":
                searchRequestByUserId(request, response);
                break;
            case "requestApprove":
                showRequestApproveForm(request, response);
                break;
            default:

                break;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "borrow":
                sendBorrowingRequest(request, response);
                break;
            default:

                break;
        }

    }

    private void sendBorrowingRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        boolean borrowingResult = false;
        int libraryElementId = Integer.parseInt(request.getParameter("libraryElementId"));
        int libraryUserId = (int) session.getAttribute("userId");
        LibraryElement libraryElement = libraryElementDao.getLibraryElementById(libraryElementId);

        if (libraryElementDao.getLibraryElementStatus(libraryElementId) == 1) {

            if (validateBorrowingProcess(libraryElementId, libraryUserId) == true) {

                borrowingResult = true;
                request.setAttribute("borrowingResult", borrowingResult);
                request.setAttribute("libraryElement", libraryElement);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowInfo.jsp");
                requestDispatcher.forward(request, response);

            } else {

                borrowingResult = false;
                request.setAttribute("borrowingResult", borrowingResult);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowInfo.jsp");
                requestDispatcher.forward(request, response);

            }

        } else {

            borrowingResult = false;
            request.setAttribute("borrowingResult", borrowingResult);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowInfo.jsp");
            requestDispatcher.forward(request, response);

        }

    }

    private boolean validateBorrowingProcess(int libraryElementId, int libraryUserId) {

        long currentTime = System.currentTimeMillis();
        java.sql.Timestamp borrowingDate = new java.sql.Timestamp(currentTime);
        java.sql.Timestamp requestDate = new java.sql.Timestamp(currentTime);

        Borrowing borrowing = new Borrowing(libraryElementId, borrowingDate, 2, libraryUserId);
        borrowingDao.insertBorrowing(borrowing);
        int borrowingId = borrowingDao.getLastBorrowingId(borrowing);

        if (borrowingId > 0 ) {
            Request newRequest = new Request(borrowingId, requestDate, 2);

            if (requestDao.insertRequest(newRequest) > 0  && libraryElementDao.updateLibraryElementStatus(libraryElementId, 2) == true) {
                return true;
            }
        }

        libraryElementDao.updateLibraryElementStatus(libraryElementId, 1);

        return false;

    }

    private void showRequestsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Request> requestsList = new ArrayList<Request>();
        requestsList = requestDao.getAllRequests();
        request.setAttribute("requestsList", requestsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("requests.jsp");
        requestDispatcher.forward(request, response);

    }

    private void searchRequestByUserId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("searchedUserId"));
        List<Request> requestsList = new ArrayList<Request>();
        requestsList = requestDao.getRequestsByUserId(userId);

        request.setAttribute("requestsList", requestsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("requests.jsp");
        requestDispatcher.forward(request, response);

    }

    private void showRequestApproveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        Request editedRequest = requestDao.getRequestById(requestId);

        int borrowingId = editedRequest.getBorrowingId();
        Borrowing borrowing = borrowingDao.getBorrowingById(borrowingId);

        LibraryUser libraryUser = libraryUserDao.getLibraryUserByBorrowingId(borrowingId);
        LibraryElement libraryElement = libraryElementDao.getLibraryElementByBorrowingId(borrowingId);


        request.setAttribute("libraryUser", libraryUser);
        request.setAttribute("libraryElement", libraryElement);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("requestApproveForm.jsp");
        requestDispatcher.forward(request, response);

    }

}
