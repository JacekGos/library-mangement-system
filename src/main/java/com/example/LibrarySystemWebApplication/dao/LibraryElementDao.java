package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.example.LibrarySystemWebApplication.model.Book;
import com.example.LibrarySystemWebApplication.model.LibraryElement;
import com.example.LibrarySystemWebApplication.model.Movie;

import java.util.List;
import java.util.ArrayList;

public class LibraryElementDao {

    static Connection connection;

    static {
        try {
            connection = DataBaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static final String INSERT_BOOK = "INSERT INTO [LibraryProject_v2].[dbo].[Library_element]"
            + "VALUES (?, ?, ?, ?, NULL, ?)";
    public static final String SELECT_ALL_LIBRARY_ELEMENTS = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element]";
    public static final String SELECT_LIBRARY_ELEMENTS_BY_TITLE = "SELECT * FROM [LibraryProject_v2].[dbo].[Library_element]" +
            "WHERE name = ?";


    public static int insertBook(Book book) {

        int status = 0;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getTypeId());
            preparedStatement.setInt(3, book.getSortId());
            preparedStatement.setInt(4, book.getPagesNumber());
            preparedStatement.setInt(5, book.getStatusId());

            status = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return status;
    }

    public static List<LibraryElement> getAllLibraryElements() {

        List<LibraryElement> libraryElementsList = new ArrayList<LibraryElement>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIBRARY_ELEMENTS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("library_element_id");
                String title = resultSet.getString("title");
                byte typeId = resultSet.getByte("type_id");
                int sortId = resultSet.getInt("sort_id");
                int statusId = resultSet.getInt("status_id");
                if (typeId == 1) {
                    int pagesNumber = resultSet.getInt("pages_number");
                    Book book = new Book(id, typeId, title, sortId, statusId, pagesNumber);
                    libraryElementsList.add(book);
                } else if (typeId == 2) {
                    int durationTime = resultSet.getInt("duration_time");
                    Movie movie = new Movie(id, typeId, title, sortId, statusId, durationTime);
                    libraryElementsList.add(movie);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryElementsList;
    }

    public static List<LibraryElement> getLibraryElementsByTitle(String searchedTitle) {

        List<LibraryElement> libraryElementsList = new ArrayList<LibraryElement>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_ELEMENTS_BY_TITLE);
            preparedStatement.setString(1, searchedTitle);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("library_element_id");
                String title = resultSet.getString("title");
                byte typeId = resultSet.getByte("type_id");
                int sortId = resultSet.getInt("sort_id");
                int statusId = resultSet.getInt("status_id");
                if (typeId == 1) {
                    int pagesNumber = resultSet.getInt("pages_number");
                    Book book = new Book(id, typeId, title, sortId, statusId, pagesNumber);
                    libraryElementsList.add(book);
                } else if (typeId == 2) {
                    int durationTime = resultSet.getInt("duration_time");
                    Movie movie = new Movie(id, typeId, title, sortId, statusId, durationTime);
                    libraryElementsList.add(movie);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return libraryElementsList;
    }
}
