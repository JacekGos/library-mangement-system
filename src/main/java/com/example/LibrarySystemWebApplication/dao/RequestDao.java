package com.example.LibrarySystemWebApplication.dao;

import com.example.LibrarySystemWebApplication.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public static final String INSERT_REQUEST = "INSERT INTO public.\"Request\""
            + " (borrowing_id, request_date, status_id) VALUES (?, ?, ?)";

    public static final String SELECT_ALL_REQUESTS = "SELECT * FROM public.\"Request\"";

    public static final String SELECT_REQUESTS_BY_ID = "SELECT request_id, borrowing_id, request_date, status_id" +
            " FROM public.\"Request\" WHERE request_id = ?";

    public static final String SELECT_REQUESTS_BY_USER_ID = "SELECT request_id, r.borrowing_id, request_date, r.status_id" +
            " FROM public.\"Request\" r" +
            " INNER JOIN public.\"Borrowings\" b" +
            " ON r.borrowing_id = b.borrowing_id" +
            " WHERE library_user_id = ?";

    public static final String UPDATE_REQUEST_STATUS = "UPDATE public.\"Request\"" +
            " SET status_id = ? WHERE request_id = ?";

    public static final String DELETE_REQUEST_BY_USER_ID = "DELETE FROM public.\"Request\" r" +
            " USING public.\"Borrowings\" b" +
            " WHERE r.borrowing_id = b.borrowing_id" +
            " AND b.library_user_id = ?";

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

    public static List<Request> getAllRequests() {

        List<Request> requestList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("request_id");
                int borrowingId = resultSet.getInt("borrowing_id");
                Timestamp requestDate = resultSet.getTimestamp("request_date");
                int statusId = resultSet.getInt("status_id");

                Request request = new Request(id, borrowingId, requestDate, statusId);

                requestList.add(request);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return requestList;
    }

    public static Request getRequestById(int requestId) {

        Request request = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUESTS_BY_ID);
            preparedStatement.setInt(1, requestId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("request_id");
                int borrowingId = resultSet.getInt("borrowing_id");
                Timestamp requestDate = resultSet.getTimestamp("request_date");
                int statusId = resultSet.getInt("status_id");

                request = new Request(id, borrowingId, requestDate, statusId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return request;
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
                Timestamp requestDate = resultSet.getTimestamp("request_date");
                int statusId = resultSet.getInt("status_id");

                Request request = new Request(id, borrowingId, requestDate, statusId);

                requestList.add(request);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return requestList;
    }

    public static boolean updateRequestStatus(int requestId, int statusId) {

        boolean rowUpdated = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS);
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, requestId);

            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowUpdated;
    }

    public static boolean deleteALLRequests(int libraryUserId) {

        boolean rowsDeleted = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_BY_USER_ID);

            preparedStatement.setInt(1, libraryUserId);

            rowsDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowsDeleted;
    }

}
