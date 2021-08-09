package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.BorrowingDao;
import com.example.LibrarySystemWebApplication.dao.LibraryElementDao;
import com.example.LibrarySystemWebApplication.dao.LibraryUserDao;
import com.example.LibrarySystemWebApplication.dao.RequestDao;
import com.example.LibrarySystemWebApplication.model.*;

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
public class UserServlet extends HttpServlet implements DataInputHelper {

    private final LibraryElementDao libraryElementDao = new LibraryElementDao();
    private final LibraryUserDao libraryUserDao = new LibraryUserDao();
    private final BorrowingDao borrowingDao = new BorrowingDao();
    private final RequestDao requestDao = new RequestDao();

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
            case "userPenalty":
                showPenaltyMenu(request, response);
                break;
            case "regulatePenalty":
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
            case "regulatePenalty":
                regulatePenalty(request, response);
                break;
            default:
                break;
        }

    }

    private void libraryUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();
        libraryUserList = libraryUserDao.getAllLibraryUsers();
        request.setAttribute("libraryUserList", libraryUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userList.jsp");
        requestDispatcher.forward(request, response);

    }

    private void searchLibraryUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> errorMessageList = new ArrayList<>();
        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();
        boolean isDataIncorrect = false;

        String searchedUserId = request.getParameter("searchedUserId");
        if (validateIntData(searchedUserId)) {
            int userId = Integer.parseInt(searchedUserId);
            libraryUserList = libraryUserDao.getLibraryUsersById(userId);
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
            libraryUserList(request, response);
            return;
        }

        request.setAttribute("libraryUserList", libraryUserList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userList.jsp");
        requestDispatcher.forward(request, response);

    }

    private void addLibraryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/addLibraryUserForm.jsp");
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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userData.jsp");
        requestDispatcher.forward(request, response);

    }

    private void insertLibraryUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<String> errorMessageList = new ArrayList<>();
        boolean isDataIncorrect = false;
        LibraryUser libraryUser = null;
        String userName = request.getParameter("userName");
        String userSurname = request.getParameter("userSurname");
        String userPassword = request.getParameter("userPassword");

        errorMessageList = getErrorMessages(userName, userSurname, userPassword);

        if (!validateStringData(userName)) {
            userName = "";
        }
        if (!validateStringData(userSurname)) {
            userSurname = "";
        }
        if (!validateStringData(userPassword)) {
            userPassword = "";
        }

        if (!errorMessageList.isEmpty()) {
            isDataIncorrect = true;
            request.setAttribute("isDataIncorrect", isDataIncorrect);
            request.setAttribute("userName", userName);
            request.setAttribute("userSurname", userSurname);
            request.setAttribute("userPassword", userPassword);
            request.setAttribute("errorMessageList", errorMessageList);
            addLibraryUser(request, response);
            return;
        }

        String login = loginGenerator(userName, userSurname);

        libraryUser = new LibraryUser(0, userName, userSurname, login, userPassword, 2, 0.0);

        libraryUserDao.insertLibraryUser(libraryUser);

        request.setAttribute("libraryUser", libraryUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/accountInfo.jsp");
        requestDispatcher.forward(request, response);

    }


    private void userInfoListAfterEndBorrowing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));
        List<Borrowing> libraryUserBorrowingsList = borrowingDao.getAllBorrowingsByUserIdAndStatus(libraryUserId);
        request.setAttribute("libraryUserBorrowingsList", libraryUserBorrowingsList);

        request.setAttribute("libraryUserId", libraryUserId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userData.jsp");
        requestDispatcher.forward(request, response);

    }

    private void showPenaltyMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));
        double userPenalty = libraryUserDao.getLibraryUserPenalty(libraryUserId);

        request.setAttribute("libraryUserId", libraryUserId);
        request.setAttribute("userPenalty", userPenalty);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/penaltyMenu.jsp");
        requestDispatcher.forward(request, response);
    }

    private void regulatePenalty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> errorMessageList = new ArrayList<>();
        boolean isDataIncorrect = false;

        int libraryUserId = Integer.parseInt(request.getParameter("libraryUserId"));
        double userPenalty = libraryUserDao.getLibraryUserPenalty(libraryUserId);

        Double returnedAmount = null;
        String returnedAmountParam = request.getParameter("returnedAmount");
        if (validateDoubleData(returnedAmountParam)) {
            returnedAmount = Double.parseDouble(returnedAmountParam);
        }

        if (!validateDoubleData(returnedAmountParam) || !validateSignDoubleData(returnedAmount)) {
            returnedAmountParam = "";
        }

        errorMessageList = getErrorMessages(returnedAmountParam, returnedAmount);

        if (!errorMessageList.isEmpty()) {
            isDataIncorrect = true;
            request.setAttribute("isDataIncorrect", isDataIncorrect);
            request.setAttribute("returnedAmountParam", returnedAmountParam);
            request.setAttribute("errorMessageList", errorMessageList);
            showPenaltyMenu(request, response);
            return;
        }

        if (userPenalty <=  returnedAmount) {
            userPenalty = 0;
        } else {
            userPenalty -= returnedAmount;
        }

        libraryUserDao.updateLibraryUserPenalty(userPenalty, libraryUserId);
        libraryUserList(request, response);

    }

    private List<String> getErrorMessages(String userName, String userSurname, String password) {

        List<String> errorMessageList = new ArrayList<>();

        if (DataInputHelper.checkEmpty(userName) || DataInputHelper.isFirstCharEmpty(userName)
                || DataInputHelper.checkLength(userName)) {
            errorMessageList.add("Nieprawdiłowe imię!");
        }
        if (DataInputHelper.checkEmpty(userSurname) || DataInputHelper.isFirstCharEmpty(userSurname)
                || DataInputHelper.checkLength(userSurname)) {
            errorMessageList.add("Nieprawdiłowe nazwisko!");
        }
        if (DataInputHelper.checkEmpty(password) || DataInputHelper.isFirstCharEmpty(password)
                || DataInputHelper.checkLength(password)) {
            errorMessageList.add("Nieprawdiłowe hasło!");
        }

        return errorMessageList;
    }

    private List<String> getErrorMessages(String userData) {

        List<String> errorMessageList = new ArrayList<>();

        if (!DataInputHelper.isConvertableToInt(userData)) {
            errorMessageList.add("Nieprawdiłowy ID Użytkownika!");
        }

        return errorMessageList;
    }

    private List<String> getErrorMessages(String userData, Double userDoubleData) {

        List<String> errorMessageList = new ArrayList<>();

        if (!DataInputHelper.isConvertableToDouble(userData) || userDoubleData < 0) {
            errorMessageList.add("Nieprawdiłowa kwota!");
        }

        return errorMessageList;
    }




    private boolean validateStringData(String userData) {

        if (DataInputHelper.checkEmpty(userData) || DataInputHelper.isFirstCharEmpty(userData)
                || DataInputHelper.checkLength(userData)) {
            return false;
        }
        return true;
    }

    private boolean validateIntData(String userData) {

        if (DataInputHelper.isConvertableToInt(userData)) {
            return true;
        }
        return false;
    }

    private boolean validateDoubleData(String userData) {

        if (DataInputHelper.isConvertableToDouble(userData)) {
            return true;
        }
        return false;
    }

    private boolean validateSignDoubleData(Double userData) {

        if (userData >= 0) {
            return true;
        }
        return false;
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


}
