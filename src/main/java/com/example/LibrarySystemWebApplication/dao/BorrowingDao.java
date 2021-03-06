package com.example.LibrarySystemWebApplication.dao;

import com.example.LibrarySystemWebApplication.Utility;
import com.example.LibrarySystemWebApplication.model.Borrowing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class BorrowingDao {

    private static final BorrowingDao borrowingDao = new BorrowingDao();
    private static final LibraryElementDao libraryElementDao = new LibraryElementDao();

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

    public static final String SELECT_BORROWINGS_BY_USER_ID_AND_STATUS = "SELECT * FROM public.\"Borrowings\" " +
            "WHERE library_user_id = ? ORDER BY status_id ASC LIMIT 10";

    public static final String SELECT_BORROWINGS_BY_USER_ID = "SELECT * FROM public.\"Borrowings\" " +
            "WHERE library_user_id = ? ORDER BY borrowing_id DESC LIMIT 10";

    public static final String SELECT_BORROWING_BY_ID = "SELECT * FROM public.\"Borrowings\" WHERE borrowing_id = ?";

    public static final String SELECT_LIBRARY_ELEMENT_ID = "SELECT element_id FROM public.\"Borrowings\" WHERE borrowing_id = ?";

    public static final String UPDATE_BORROWING_STATUS = "UPDATE public.\"Borrowings\"" +
            " SET status_id = ? WHERE borrowing_id = ?";

    public static final String UPDATE_BORROWING_DATE = "UPDATE public.\"Borrowings\"" +
            " SET borrowing_date = ? WHERE borrowing_id = ?";

    public static final String DELETE_BORROWING = "DELETE FROM public.\"Borrowings\"" +
            " WHERE borrowing_id = ?";

    public static final String DELETE_ALL_USER_BORROWINGS = "DELETE FROM public.\"Borrowings\"" +
            " WHERE library_user_id = ?";

    public static final String DELETE_BORROWINGS_BY_LIBRARY_ELEMENT_ID = "DELETE FROM public.\"Borrowings\"" +
            " WHERE element_id = ?";


    public static int insertBorrowing(Borrowing borrowing) {

        int status = 0;

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROWING, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, borrowing.getLibraryElementId());
            preparedStatement.setTimestamp(2, borrowing.getBorrowingDate());
            preparedStatement.setInt(3, borrowing.getBorrowingStatusId());
            preparedStatement.setInt(4, borrowing.getLibraryUserId());

            status = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    borrowing.setBorrowingId(generatedKeys.getInt(1));
                }
            }
            connection.commit();

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        return status;
    }
    
    public static List<Borrowing> getAllBorrowingsByUserIdAndStatus(int userId) {

        List<Borrowing> borrowingsList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWINGS_BY_USER_ID_AND_STATUS);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt("borrowing_id");
                int elementId = resultSet.getInt("element_id");
                Timestamp date = resultSet.getTimestamp("borrowing_date");
                int statusId = resultSet.getInt("status_id");
                int libraryUserId = resultSet.getInt("library_user_id");

                Borrowing borrowing = new Borrowing(borrowingId, elementId, date, statusId, libraryUserId);
                borrowingsList.add(borrowing);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return borrowingsList;
    }

    public static List<Borrowing> getAllBorrowingsByUserId(int userId) {

        List<Borrowing> borrowingsList = new ArrayList<Borrowing>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWINGS_BY_USER_ID);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt("borrowing_id");
                int elementId = resultSet.getInt("element_id");
                Timestamp date = resultSet.getTimestamp("borrowing_date");
                int statusId = resultSet.getInt("status_id");
                int libraryUserId = resultSet.getInt("library_user_id");

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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROWING_BY_ID);
            preparedStatement.setInt(1, searchedBorrowingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int borrowingId = resultSet.getInt("borrowing_id");
                int elementId = resultSet.getInt("element_id");
                Timestamp date = resultSet.getTimestamp("borrowing_date");
                int statusId = resultSet.getInt("status_id");
                int libraryUserId = resultSet.getInt("library_user_id");

                borrowing = new Borrowing(borrowingId, elementId, date, statusId, libraryUserId);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return borrowing;
    }

    public static int getLibraryElementId(int borrowingId) {

        int libraryElementId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_ELEMENT_ID);
            preparedStatement.setInt(1, borrowingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                libraryElementId = resultSet.getInt("element_id");


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryElementId;
    }

    public static boolean updateBorrowingStatus(int borrowingId, int statusId) {

        boolean rowUpdated = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BORROWING_STATUS);
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, borrowingId);

            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowUpdated;
    }

    public static boolean updateBorrowingDate(int borrowingId, Timestamp acceptDate) {

        boolean rowUpdated = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BORROWING_DATE);
            preparedStatement.setTimestamp(1, acceptDate);
            preparedStatement.setInt(2, borrowingId);

            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowUpdated;
    }


    public static boolean deleteALLBorrowings(int libraryUserId) {

        boolean rowDeleted = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USER_BORROWINGS);

            preparedStatement.setInt(1, libraryUserId);

            rowDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowDeleted;
    }

    public static boolean deleteALLBorrowingsByLibraryElementId(int libraryElementId) {

        boolean rowDeleted = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BORROWINGS_BY_LIBRARY_ELEMENT_ID);

            preparedStatement.setInt(1, libraryElementId);

            rowDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowDeleted;
    }
    
    public static boolean acceptEndBorrowingProcess(int borrowingId, int libraryElementId) {

        try {
            connection.setAutoCommit(false);
            borrowingDao.updateBorrowingStatus(borrowingId, Utility.STATUS_FINISHED);
            libraryElementDao.updateLibraryElementStatus(libraryElementId, Utility.STATUS_AVAILABLE);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }

        return true;
    }

}
