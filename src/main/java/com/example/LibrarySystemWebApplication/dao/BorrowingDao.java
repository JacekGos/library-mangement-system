package com.example.LibrarySystemWebApplication.dao;

import com.example.LibrarySystemWebApplication.model.Borrowing;
import com.example.LibrarySystemWebApplication.model.LibraryUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static final String SELECT_BORROWINGS_BY_ID = "SELECT * FROM [LibraryProject_v2].[dbo].[Borrowings]" +
            " WHERE library_user_id = ?";

    public static List<Borrowing> getAllBorrowingsByUserId(int userId) {

        List<Borrowing> borrowingsList = new ArrayList<Borrowing>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWINGS_BY_ID);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt(1);
                int elementId = resultSet.getInt(2);
                java.sql.Timestamp date = resultSet.getTimestamp(3);
                int statusId = resultSet.getInt(4);
                int libraryUserId = resultSet.getInt(5);

                Borrowing borrowing = new Borrowing(borrowingId, elementId, date, statusId, libraryUserId);
                borrowingsList.add(borrowing);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return borrowingsList;
    }

}
