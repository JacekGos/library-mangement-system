package com.example.LibrarySystemWebApplication.dao;

import com.example.LibrarySystemWebApplication.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static final String SELECT_ALL_REQUESTS = "SELECT * FROM public.\"Request\"";
    public static final String SELECT_REQUESTS_BY_USER_ID = "SELECT request_id, r.borrowing_id, request_date, r.status_id" +
            " FROM public.\"Request\" r" +
            " INNER JOIN public.\"Borrowings\" b" +
            " ON r.borrowing_id = b.borrowing_id" +
            " WHERE library_user_id = ?";

    public static final String INSERT_REQUEST = "INSERT INTO public.\"Request\""
            + " (borrowing_id, request_date, status_id) VALUES (?, ?, ?)";


    public static List<Request> getAllRequests() {

        List<Request> requestList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("request_id");
                int borrowingId = resultSet.getInt("borrowing_id");
                java.sql.Timestamp requestDate = resultSet.getTimestamp("request_date");
                int statusId = resultSet.getInt("status_id");

                Request request = new Request(id, borrowingId, requestDate, statusId);

                requestList.add(request);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return requestList;
    }

    public static List<Request> getRequestsByUserId(int userId) {

        List<Request> requestList = new ArrayList<Request>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUESTS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("request_id");
                int borrowingId = resultSet.getInt("borrowing_id");
                java.sql.Timestamp requestDate = resultSet.getTimestamp("request_date");
                int statusId = resultSet.getInt("status_id");

                Request request = new Request(id, borrowingId, requestDate, statusId);

                requestList.add(request);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return requestList;
    }

    public static int insertRequest(Request request) {

        int status = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST);
            preparedStatement.setInt(1, request.getBorrowingId());
            preparedStatement.setTimestamp(2, request.getRequestDate());
            preparedStatement.setInt(3, request.getStatusId());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }


}
