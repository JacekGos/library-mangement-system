package com.example.LibrarySystemWebApplication.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryUser extends User implements DataDisplayHelper {

    private double penalty;
    private List<Borrowing> userBorrowingsList = new ArrayList<Borrowing>();

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

    public void addBorrowing(int borrowingId, int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

        Borrowing borrowing = new Borrowing(borrowingId, libraryElementId, borrowingDate, borrowingStatusId, libraryUserId);
        userBorrowingsList.add(borrowing);

    }

    private class Borrowing {

        private int borrowingId;
        private int libraryElementId;
        private java.sql.Timestamp borrowingDate;
        private int borrowingStatusId;
        private int libraryUserId;

        private Borrowing(int borrowingId, int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

            this.borrowingId = borrowingId;
            this.libraryElementId = libraryElementId;
            this.borrowingDate = borrowingDate;
            this.borrowingStatusId = borrowingStatusId;
            this.libraryUserId = libraryUserId;

        }

        private Borrowing() {}

        public int getBorrowingId() {
            return borrowingId;
        }

        public void setBorrowingId(int borrowingId) {
            this.borrowingId = borrowingId;
        }

        public int getLibraryElementId() {
            return libraryElementId;
        }

        public void setLibraryElementId(int libraryElementId) {
            this.libraryElementId = libraryElementId;
        }

        public java.sql.Timestamp getBorrowingDate() {
            return borrowingDate;
        }

        public void setBorrowingDate(java.sql.Timestamp borrowingDate) {
            this.borrowingDate = borrowingDate;
        }

        public int getBorrowingStatusId() {
            return borrowingStatusId;
        }

        public void setBorrowingStatusId(int borrowingStatusId) {
            this.borrowingStatusId = borrowingStatusId;
        }

        public int getLibraryUserId() {
            return libraryUserId;
        }

        public void setLibraryUserId(int libraryUserId) {
            this.libraryUserId = libraryUserId;
        }

        public String getStatusName () {
            return DataDisplayHelper.convertStatusToString(this.borrowingStatusId);
        }

    }

}
