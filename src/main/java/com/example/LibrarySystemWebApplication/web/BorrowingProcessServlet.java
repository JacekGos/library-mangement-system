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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@WebServlet(name = "borrrowingProcess", value = "/borrowingProcess")
public class BorrowingProcessServlet extends HttpServlet {

    private LibraryElementDao libraryElementDao = new LibraryElementDao();
    private LibraryUserDao libraryUserDao = new LibraryUserDao();
    private BorrowingDao borrowingDao = new BorrowingDao();
    private RequestDao requestDao = new RequestDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "borrowLibraryElement":
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
            case "showBorrowings":
                showBorrowingsList(request, response);
                break;
            case "endBorrowing":
                doPost(request, response);
                break;
            default:
                break;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "borrowLibraryElement":
                sendBorrowingRequest(request, response);
                break;
            case "rejectRequest":
                rejectRequest(request, response);
                break;
            case "acceptRequest":
                acceptRequest(request, response);
                break;
            case "endBorrowing":
                endBorrowing(request, response);
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
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/borrowInfo.jsp");
                requestDispatcher.forward(request, response);

            } else {

                borrowingResult = false;
                request.setAttribute("borrowingResult", borrowingResult);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/borrowInfo.jsp");
                requestDispatcher.forward(request, response);

            }

        } else {

            borrowingResult = false;
            request.setAttribute("borrowingResult", borrowingResult);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/borrowInfo.jsp");
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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/requests.jsp");
        requestDispatcher.forward(request, response);

    }

    private void searchRequestByUserId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> errorMessageList = new ArrayList<>();
        List<Request> requestsList = new ArrayList<Request>();
        boolean isDataIncorrect = false;

        String searchedUserId = request.getParameter("searchedUserId");
        if (validateIntData(searchedUserId)) {
            int userId = Integer.parseInt(searchedUserId);
            requestsList = requestDao.getRequestsByUserId(userId);
        }

        if (!validateIntData(searchedUserId)) {
            searchedUserId = "";
        }

        errorMessageList = getErrorMessages(searchedUserId);

        if (!errorMessageList.isEmpty()) {
            isDataIncorrect = true;
            request.setAttribute("isDataIncorrect", isDataIncorrect);
            request.setAttribute("searchedUserId", searchedUserId);
            request.setAttribute("errorMessageList", errorMessageList);
            showRequestsList(request, response);
            return;
        }

        request.setAttribute("requestsList", requestsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/requests.jsp");
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
        request.setAttribute("requestId", requestId);
        request.setAttribute("borrowingId", borrowingId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/requestApproveForm.jsp");
        requestDispatcher.forward(request, response);

    }

    private void rejectRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int borrowingId = Integer.parseInt(request.getParameter("borrowingId"));
        int libraryElementId = Integer.parseInt(request.getParameter("libraryElementId"));

        requestDao.updateRequestStatus(requestId, 5);
        borrowingDao.updateBorrowingStatus(borrowingId, 5);
        libraryElementDao.updateLibraryElementStatus(libraryElementId, 1);

        request.removeAttribute("requestId");
        request.removeAttribute("borrowingId");
        request.removeAttribute("libraryElementId");

        showRequestsList(request, response);

    }

    private void acceptRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        long currentTime = System.currentTimeMillis();
        java.sql.Timestamp acceptBorrowingDate = new java.sql.Timestamp(currentTime);

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        int borrowingId = Integer.parseInt(request.getParameter("borrowingId"));
        int libraryElementId = Integer.parseInt(request.getParameter("libraryElementId"));

        requestDao.updateRequestStatus(requestId, 4);
        borrowingDao.updateBorrowingStatus(borrowingId, 4);
        borrowingDao.updateBorrowingDate(borrowingId, acceptBorrowingDate);
        libraryElementDao.updateLibraryElementStatus(libraryElementId, 3);

        request.removeAttribute("requestId");
        request.removeAttribute("borrowingId");
        request.removeAttribute("libraryElementId");

        showRequestsList(request, response);

    }

    private void showBorrowingsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int libraryUserId = (int) session.getAttribute("userId");
        List<Borrowing> libraryUserBorrowingsList = borrowingDao.getAllBorrowingsByUserId(libraryUserId);
        request.setAttribute("libraryUserBorrowingsList", libraryUserBorrowingsList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userData.jsp");
        requestDispatcher.forward(request, response);

    }

    private void endBorrowing(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int borrowingId = Integer.parseInt(request.getParameter("borrowingId"));
        Borrowing borrowing = borrowingDao.getBorrowingById(borrowingId);
        int libraryElementId = borrowing.getLibraryElementId();
        int libraryUserId = borrowing.getLibraryUserId();

        borrowingDao.updateBorrowingStatus(borrowingId, 6);
        libraryElementDao.updateLibraryElementStatus(libraryElementId, 1);

        request.removeAttribute("borrowingId");
        request.removeAttribute("libraryElementId");

        checkReturningTime(request, response, borrowing, libraryUserId);

    }

    private void checkReturningTime(HttpServletRequest request, HttpServletResponse response, Borrowing borrowing, int libraryUserId)
            throws ServletException, IOException {

        boolean returningResult = true;
        double penalty = 0;
        double currentUserPenalty = 0;

        long currentTime = System.currentTimeMillis();
        Timestamp borrowingTime = borrowing.getBorrowingDate();
        long timeToReturn = 30000;

        long differenceTime = currentTime - borrowingTime.getTime();
        int calculatedSeconds = (int) (differenceTime / 1000);
        int minutes = (calculatedSeconds % 3600) / 60;
        int hours = (calculatedSeconds / 3600) % 24;
        int days = calculatedSeconds / 86400;
        int seconds = (calculatedSeconds % 3600) % 60;

        if (timeToReturn > differenceTime)
        {
            returningResult = true;
        } else {

            returningResult = false;
            penalty = calculatePenalty(differenceTime);
            currentUserPenalty = libraryUserDao.getLibraryUserPenalty(libraryUserId);
            currentUserPenalty += penalty;
            libraryUserDao.updateLibraryUserPenalty(currentUserPenalty, libraryUserId);

        }

        request.setAttribute("returningResult", returningResult);
        request.setAttribute("borrowingTime", borrowingTime);
        request.setAttribute("days", abs(days));
        request.setAttribute("hours", abs(hours));
        request.setAttribute("minutes", abs(minutes));
        request.setAttribute("seconds", abs(seconds));
        request.setAttribute("penalty", abs(penalty));
        request.setAttribute("libraryUserId", libraryUserId);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("borrowing/returnInfo.jsp");
        requestDispatcher.forward(request, response);

    }

    private double calculatePenalty(long differenceTime) {

        double penalty = 0.0f;

        int calculatedSeconds = (int) (differenceTime / 1000);
        int minutes = calculatedSeconds / 60;

        penalty = (double) (0.01 * minutes);

        return penalty;
    }

    private List<String> getErrorMessages(String searchedUserId) {

        List<String> errorMessageList = new ArrayList<>();

        if (!DataInputHelper.isConvertableToInt(searchedUserId)) {
            errorMessageList.add("Nieprawdiłowy ID Użytkownika!");
        }

        return errorMessageList;
    }

    private boolean validateIntData(String userData) {

        if (DataInputHelper.isConvertableToInt(userData)) {
            return true;
        }
        return false;
    }

}
