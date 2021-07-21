package com.example.LibrarySystemWebApplication.model;

public class Request {

    private int requestId;
    private int borrowingId;
    private java.sql.Timestamp requestDate;
    private int statusId;

    public Request(int requestId, int borrowingId, java.sql.Timestamp requestDate, int statusId) {

        this.setRequestId(requestId);
        this.setBorrowingId(borrowingId);
        this.setRequestDate(requestDate);
        this.setStatusId(statusId);

    }

    public Request(int borrowingId, java.sql.Timestamp requestDate, int statusId) {

        this.setBorrowingId(borrowingId);
        this.setRequestDate(requestDate);
        this.setStatusId(statusId);

    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public java.sql.Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(java.sql.Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return DataDisplayHelper.convertStatusToString(this.statusId);
    }
}