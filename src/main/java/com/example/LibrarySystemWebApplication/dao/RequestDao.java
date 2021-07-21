package com.example.LibrarySystemWebApplication.dao;

import com.example.LibrarySystemWebApplication.model.LibraryUser;
import com.example.LibrarySystemWebApplication.model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
