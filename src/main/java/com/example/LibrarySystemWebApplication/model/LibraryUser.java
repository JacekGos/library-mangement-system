package com.example.LibrarySystemWebApplication.model;

import java.util.List;

public class LibraryUser extends User {

    private double penalty;
//    private List<Borrowing> userBorrowingsList = new ArrayList<Borrowing>();

    public LibraryUser(int userId, String userName, String userSurName, String login, String password, int accountType, double penalty) {
        super(userId, userName, userSurName, login, password, accountType);
        this.penalty = penalty;
    }

    public LibraryUser(int userId, String userName, String userSurName, String login, double penalty) {

        this.setUserId(userId);
        this.setUserName(userName);
        this.setUserSurName(userSurName);
        this.setLogin(login);
        this.penalty = penalty;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

}
