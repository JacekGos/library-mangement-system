package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.ResultSet;
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

    //MS SQL SERVER
  /*  public static final String INSERT_LIBRARY_WORKER = "INSERT INTO [LibraryProject_v2].[dbo].[Librarian]"
            + "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_WORKER_BY_NAME_AND_PASSWORD = "SELECT * FROM [LibraryProject_v2].[dbo].[Librarian]" +
            " WHERE login = ? AND password = ?";
*/

    public static final String INSERT_LIBRARY_WORKER = "INSERT INTO public.\"Librarian\""
            + "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_WORKER_BY_NAME_AND_PASSWORD = "SELECT * FROM public.\"Librarian\"" +
            " WHERE login = ? AND password = ?";


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

    public static LibraryWorker getLibraryWorkerByLoginAndPasword(String login, String password) {

        LibraryWorker libraryWorker = new LibraryWorker();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WORKER_BY_NAME_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                libraryWorker.setUserId(resultSet.getInt(1));
                libraryWorker.setUserName(resultSet.getString(2));
                libraryWorker.setUserSurName(resultSet.getString(3));
                libraryWorker.setLogin(resultSet.getString(4));
                libraryWorker.setPassword(resultSet.getString(5));
                libraryWorker.setAccountType(resultSet.getInt(6));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryWorker;

    }
}
