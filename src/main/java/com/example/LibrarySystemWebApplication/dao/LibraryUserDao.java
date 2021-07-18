package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.example.LibrarySystemWebApplication.model.Book;
import com.example.LibrarySystemWebApplication.model.LibraryElement;
import com.example.LibrarySystemWebApplication.model.LibraryUser;
import com.example.LibrarySystemWebApplication.model.Movie;

public class LibraryUserDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static final String INSERT_LIBRARY_USER = "INSERT INTO [LibraryProject_v2].[dbo].[Library_user]"
            + " VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_LIBRARY_USERS = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user]";
    public static final String SELECT_LIBRARY_USERS_BY_ID = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user]" +
            " WHERE library_user_id = ?";
    public static final String SELECT_LIBRARY_USER_BY_ID = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user]" +
            " WHERE library_user_id = ?";
    public static final String DELETE_LIBRARY_USER = "DELETE FROM [LibraryProject_v2].[dbo].[Library_user]" +
            " WHERE library_user_id = ?";

    public static int insertLibraryUser(LibraryUser libraryUser) {

        int status = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIBRARY_USER);
            preparedStatement.setString(1, libraryUser.getUserName());
            preparedStatement.setString(2, libraryUser.getUserSurName());
            preparedStatement.setString(3, libraryUser.getLogin());
            preparedStatement.setString(4, libraryUser.getPassword());
            preparedStatement.setDouble(5, libraryUser.getPenalty());
            preparedStatement.setInt(6, libraryUser.getAccountType());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<LibraryUser> getAllLibraryUsers() {

        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIBRARY_USERS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("library_user_id");
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surName");
                String login = resultSet.getString("login");
                double penalty = resultSet.getDouble("penalty");

                LibraryUser libraryUser = new LibraryUser(id, name, surName, login, penalty);
                libraryUserList.add(libraryUser);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUserList;
    }


    public static List<LibraryUser> getLibraryUsersById(int userId) {

        List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_USERS_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                int id = resultSet.getInt("library_user_id");
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surName");
                String login = resultSet.getString("login");
                double penalty = resultSet.getDouble("penalty");

                LibraryUser libraryUser = new LibraryUser(id, name, surName, login, penalty);
                libraryUserList.add(libraryUser);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUserList;
    }

    public static LibraryUser getLibraryUserById(int userId) {

        LibraryUser libraryUser = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                int id = resultSet.getInt("library_user_id");
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surName");
                String login = resultSet.getString("login");
                double penalty = resultSet.getDouble("penalty");

                libraryUser = new LibraryUser(id, name, surName, login, penalty);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUser;
    }

    //TODO - Add delete User's borrowings first
    public static boolean deleteLibraryUser(int libraryUserId) {

        boolean rowDeleted = false;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LIBRARY_USER);

            preparedStatement.setInt(1, libraryUserId);

            rowDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowDeleted;
    }

}
