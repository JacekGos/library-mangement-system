package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.example.LibrarySystemWebApplication.model.*;

public class LibraryUserDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //MS SQL SERVER
    /*public static final String INSERT_LIBRARY_USER = "INSERT INTO [LibraryProject_v2].[dbo].[Library_user]"
            + " VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_LIBRARY_USERS = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user]";
    public static final String SELECT_LIBRARY_USERS_BY_ID = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user]" +
            " WHERE library_user_id = ?";
    public static final String SELECT_LIBRARY_USER_BY_ID = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_user]" +
            " WHERE library_user_id = ?";
    public static final String DELETE_LIBRARY_USER = "DELETE FROM [LibraryProject_v2].[dbo].[Library_user]" +
            " WHERE library_user_id = ?";*/


    public static final String INSERT_LIBRARY_USER = "INSERT INTO public.\"Library_user\""
            + " (name, surname, login, password, penalty, account_type) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_LIBRARY_USERS = "SELECT * FROM public.\"Library_user\"";

    public static final String SELECT_LIBRARY_USERS_BY_ID = "SELECT * FROM public.\"Library_user\"" +
            " WHERE library_user_id = ?";

    public static final String SELECT_LIBRARY_USERS_BY_NAME = "SELECT * FROM public.\"Library_user\"" +
            " WHERE name = ? AND surname = ?";

    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM public.\"Library_user\"" +
            " WHERE login = ? AND password = ?";

    public static final String SELECT_USER_BY_BORROWING_ID= "SELECT lu.library_user_id, name, surname, login, penalty" +
            " FROM public.\"Library_user\" lu" +
            " INNER JOIN public.\"Borrowings\" b" +
            " ON lu.library_user_id = b.library_user_id" +
            " WHERE borrowing_id = ?";



    public static final String DELETE_LIBRARY_USER = "DELETE FROM public.\"Library_user\"" +
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_USERS_BY_ID);
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

    public static int getLibraryUserNumberByName(String name, String surname) {

        int amount = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_USERS_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                amount++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return amount;
    }

    public static LibraryUser getLibraryUserByLoginAndPasword(String userLogin, String userPassword) {

        LibraryUser libraryUser = null;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, userPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //TODO change index number to full column name
                int id = resultSet.getInt("library_user_id");
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surName");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                double penalty = resultSet.getDouble("penalty");
                int accuountType = resultSet.getInt("account_type");

                libraryUser = new LibraryUser(id, name, surName, login, password, accuountType, penalty);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryUser;
    }

    public static LibraryUser getLibraryUserByBorrowingId(int borrowingId) {

        LibraryUser libraryUser = null;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_BORROWING_ID);
            preparedStatement.setInt(1, borrowingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //TODO change index number to full column name
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
