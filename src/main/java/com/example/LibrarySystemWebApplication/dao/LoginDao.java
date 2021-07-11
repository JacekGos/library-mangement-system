package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private static final String SELECT_USER_BY_LOGIN_AND_PASS = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian]" +
            "WHERE login = ? AND password = ?";

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean validate(String login, String password){

        boolean status = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASS);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


}
