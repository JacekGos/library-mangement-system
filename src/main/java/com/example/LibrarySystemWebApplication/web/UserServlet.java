package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.BorrowingDao;
import com.example.LibrarySystemWebApplication.dao.LibraryElementDao;
import com.example.LibrarySystemWebApplication.dao.LibraryUserDao;
import com.example.LibrarySystemWebApplication.dao.RequestDao;
import com.example.LibrarySystemWebApplication.model.*;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@WebServlet(name = "libraryUserServlet", value = "/libraryUser")
public class UserServlet extends HttpServlet {

    private LibraryElementDao libraryElementDao = new LibraryElementDao();
    private LibraryUserDao libraryUserDao = new LibraryUserDao();
    private BorrowingDao borrowingDao = new BorrowingDao();
    private RequestDao requestDao = new RequestDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "showUserList":
                libraryUserList(request, response);
                break;
            case "searchUser":
                searchLibraryUser(request, response);
                break;
            case "deleteUser":
                doPost(request, response);
                break;
            case "newUser":
                addLibraryUser(request, response);
                break;
            case "insertUser":
                doPost(request, response);
                break;
            case "userInfo":
                userInfoList(request, response);
                break;
            case "userInfoAfterEndBorrowing":
                userInfoListAfterEndBorrowing(request, response);
                break;
            case "endBorrowing":
                doPost(request, response);
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
                deleteLibraryUser(request, response);
                break;
            case "insertUser":
                insertLibraryUser(request, response);
                break;
            case "endBorrowing":
                endBorrowing(request, response);
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

    private void searchLibraryUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("searchedUserId"));
        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();
        libraryUserList = libraryUserDao.getLibraryUsersById(userId);

        request.setAttribute("libraryUserList", libraryUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userList.jsp");
        requestDispatcher.forward(request, response);

    }

    private void addLibraryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addLibraryUserForm.jsp");
        requestDispatcher.forward(request, response);

    }

    private void deleteLibraryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));

        requestDao.deleteALLRequests(libraryUserId);
        borrowingDao.deleteALLBorrowings(libraryUserId);
        libraryUserDao.deleteLibraryUser(libraryUserId);
        libraryUserList(request, response);
    }

    private void userInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));
        List<Borrowing> libraryUserBorrowingsList = borrowingDao.getAllBorrowingsByUserId(libraryUserId);
        request.setAttribute("libraryUserBorrowingsList", libraryUserBorrowingsList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userData.jsp");
        requestDispatcher.forward(request, response);

    }

    private void insertLibraryUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        LibraryUser libraryUser = null;
        String userName = request.getParameter("userName");
        String userSurname = request.getParameter("userSurname");
        String login = loginGenerator(userName, userSurname);
        String password = request.getParameter("userPassword");

        libraryUser = new LibraryUser(0, userName, userSurname, login, password, 2, 0.0);

        libraryUserDao.insertLibraryUser(libraryUser);

        request.setAttribute("libraryUser", libraryUser);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("accountInfo.jsp");
        requestDispatcher.forward(request, response);
    }

    private String loginGenerator(String name, String surname) {

        String login = null;
        char[] nameArray = name.toCharArray();
        char[] surNameArray = surname.toCharArray();
        int amount = 0;

        nameArray[0] = Character.toLowerCase(nameArray[0]);
        surNameArray[0] = Character.toLowerCase(surNameArray[0]);

        amount = libraryUserDao.getLibraryUserNumberByName(name, surname);

        name = new String(nameArray);
        surname = new String(surNameArray);

        if (amount == 0) {
            login = name + "." + surname;
        } else {
            login = name + "." + (amount + 1) + "." + surname;
        }

        return login;

    }

    private void endBorrowing(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int borrowingId = Integer.parseInt(request.getParameter("borrowingId"));
        Borrowing borrowing = borrowingDao.getBorrowingById(borrowingId);
        int libraryElementId = borrowing.getLibraryElementId();
        int libraryUserId = borrowing.getLibraryUserId();

        borrowingDao.updateBorrowingStatus(borrowingId, 6);
        libraryElementDao.updateLibraryElementStatus(libraryElementId, 1);

        request.setAttribute("libraryUserId", libraryUserId);
        request.removeAttribute("borrowingId");
        request.removeAttribute("libraryElementId");

        checkReturningTime(request, response, borrowing);

//        userInfoListAfterEndBorrowing(request, response);

    }
    //@TODO Think about use only one method userInfoList for each action
    private void userInfoListAfterEndBorrowing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        int libraryUserId = (int) request.getAttribute("libraryUserId");
        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));
        List<Borrowing> libraryUserBorrowingsList = borrowingDao.getAllBorrowingsByUserId(libraryUserId);
        request.setAttribute("libraryUserBorrowingsList", libraryUserBorrowingsList);

        request.setAttribute("libraryUserId", libraryUserId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("userData.jsp");
        requestDispatcher.forward(request, response);

    }

    private void checkReturningTime(HttpServletRequest request, HttpServletResponse response, Borrowing borrowing)
            throws ServletException, IOException {

        boolean returningResult = true;
        float penalty = 0;

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
        }

        request.setAttribute("returningResult", returningResult);
        request.setAttribute("borrowingTime", borrowingTime);
        request.setAttribute("days", abs(days));
        request.setAttribute("hours", abs(hours));
        request.setAttribute("minutes", abs(minutes));
        request.setAttribute("seconds", abs(seconds));
        request.setAttribute("penalty", abs(penalty));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("returnInfo.jsp");
        requestDispatcher.forward(request, response);

    }

    private float calculatePenalty(long differenceTime) {

        float penalty = 0.0f;

        int calculatedSeconds = (int) (differenceTime / 1000);
        int minutes = calculatedSeconds / 60;

        penalty = (float) (0.01 * minutes);

        return penalty;
    }

}
