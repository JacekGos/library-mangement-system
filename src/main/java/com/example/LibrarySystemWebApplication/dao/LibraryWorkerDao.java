package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.example.LibrarySystemWebApplication.model.LibraryWorker;


public class LibraryWorkerDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static final String INSERT_LIBRARY_WORKER = "INSERT INTO [LibraryProject_v2].[dbo].[Librarian]"
            + "VALUES (?, ?, ?, ?, ?)";

    public static int insertLibraryWorker(LibraryWorker libraryWorker) {

        int status = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIBRARY_WORKER);
            preparedStatement.setString(1, libraryWorker.getUserName());
            preparedStatement.setString(2, libraryWorker.getUserSurName());
            preparedStatement.setString(3, libraryWorker.getLogin());
            preparedStatement.setString(4, libraryWorker.getPassword());
            preparedStatement.setInt(5, libraryWorker.getAccountType());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }



}
