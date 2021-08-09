package com.example.LibrarySystemWebApplication.model;
import java.sql.Timestamp;

public class Request {

    private int requestId;
    private int borrowingId;
    private Timestamp requestDate;
    private int statusId;

    public Request(int requestId, int borrowingId, Timestamp requestDate, int statusId) {

        this.setRequestId(requestId);
        this.setBorrowingId(borrowingId);
        this.setRequestDate(requestDate);
        this.setStatusId(statusId);

    }

    public Request(int borrowingId, Timestamp requestDate, int statusId) {

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

    public Timestamp getRequestDate() {
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