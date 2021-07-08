package com.example.LibrarySystemWebApplication.model;

public abstract class User {

    private int userId;
    private String userName;
    private String userSurName;
    private String login;
    private String password;
    private int accountType;

    public User(int userId, String userName, String userSurName, String login, String password, int accountType) {

        this.setUserId(userId);
        this.setUserName(userName);
        this.setUserSurName(userSurName);
        this.setLogin(login);
        this.setPassword(password);
        this.setAccountType(accountType);

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
