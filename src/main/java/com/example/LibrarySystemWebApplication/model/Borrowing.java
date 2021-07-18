package com.example.LibrarySystemWebApplication.model;

public class Borrowing {

    private int borrowingId;
    private int libraryElementId;
    private java.sql.Timestamp borrowingDate;
    private int borrowingStatusId;
    private int libraryUserId;

    public Borrowing(int borrowingId, int libraryElementId, java.sql.Timestamp borrowingDate, int borrowingStatusId, int libraryUserId) {

        this.borrowingId = borrowingId;
        this.libraryElementId = libraryElementId;
        this.borrowingDate = borrowingDate;
        this.borrowingStatusId = borrowingStatusId;
        this.libraryUserId = libraryUserId;

    }

    private Borrowing() {
    }

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

    public String getStatusName() {
        return DataDisplayHelper.convertStatusToString(this.borrowingStatusId);
    }

}


