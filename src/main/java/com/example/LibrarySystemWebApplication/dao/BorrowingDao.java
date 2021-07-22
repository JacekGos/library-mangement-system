package com.example.LibrarySystemWebApplication.dao;

import com.example.LibrarySystemWebApplication.model.Borrowing;
import com.example.LibrarySystemWebApplication.model.LibraryUser;
import com.example.LibrarySystemWebApplication.model.Request;

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

    //MS SQL SERVER
    /*public static final String SELECT_BORROWINGS_BY_ID = "SELECT * FROM [LibraryProject_v2].[dbo].[Borrowings]" +
            " WHERE library_user_id = ?";*/

    public static final String INSERT_BORROWING = "INSERT INTO public.\"Borrowings\""
            + " (element_id, borrowing_date, status_id, library_user_id) VALUES (?, ?, ?, ?)";

    public static final String SELECT_BORROWINGS_BY_USER_ID = "SELECT * FROM public.\"Borrowings\" WHERE library_user_id = ?";

    public static final String SELECT_BORROWINGS_BY_ID = "SELECT * FROM public.\"Borrowings\" WHERE borrowing_id = ?";

    public static final String SELECT_LAST_BORROWING_ID = "SELECT borrowing_id FROM public.\"Borrowings\"" +
            " WHERE library_user_id = ? AND element_id = ? AND borrowing_date = ?";

    public static final String DELETE_BORROWING = "DELETE FROM public.\"Borrowings\"" +
            " WHERE borrowing_id = ?";


    public static int insertBorrowing(Borrowing borrowing) {

        int status = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROWING);
            preparedStatement.setInt(1, borrowing.getLibraryElementId());
            preparedStatement.setTimestamp(2, borrowing.getBorrowingDate());
            preparedStatement.setInt(3, borrowing.getBorrowingStatusId());
            preparedStatement.setInt(4, borrowing.getLibraryUserId());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }
    
    public static List<Borrowing> getAllBorrowingsByUserId(int userId) {

        List<Borrowing> borrowingsList = new ArrayList<Borrowing>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWINGS_BY_USER_ID);
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

    public static Borrowing getBorrowingById(int searchedBorrowingId) {

        Borrowing borrowing = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWINGS_BY_ID);
            preparedStatement.setInt(1, searchedBorrowingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt(1);
                int elementId = resultSet.getInt(2);
                java.sql.Timestamp date = resultSet.getTimestamp(3);
                int statusId = resultSet.getInt(4);
                int libraryUserId = resultSet.getInt(5);

                borrowing = new Borrowing(borrowingId, elementId, date, statusId, libraryUserId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return borrowing;
    }

    public static int getLastBorrowingId(Borrowing borrowing) {

       int borrowingId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_BORROWING_ID);
            preparedStatement.setInt(1, borrowing.getLibraryUserId());
            preparedStatement.setInt(2, borrowing.getLibraryElementId());
            preparedStatement.setTimestamp(3, borrowing.getBorrowingDate());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                borrowingId = resultSet.getInt(1);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return borrowingId;
    }

}
