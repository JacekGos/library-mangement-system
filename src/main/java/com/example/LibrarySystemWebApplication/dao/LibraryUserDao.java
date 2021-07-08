package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.example.LibrarySystemWebApplication.model.LibraryUser;

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
            + "VALUES (?, ?, ?, ?, ?, ?)";

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

}
