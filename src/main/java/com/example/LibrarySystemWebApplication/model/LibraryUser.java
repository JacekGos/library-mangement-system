package com.example.LibrarySystemWebApplication.model;


public class LibraryUser extends User implements DataDisplayHelper {

    private double penalty;

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

    public LibraryUser() {}

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

}
