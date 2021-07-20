package com.example.LibrarySystemWebApplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

//    private static String url = "jdbc:sqlserver://DESKTOP-2NG6J3P;databaseName=LibraryProject_v2;integratedSecurity=true";
    private static String url = "jdbc:postgresql://localhost:5432/LibrarySystem";
    private static String jdbcUsername = "postgres";
    private static String jdbcPassword = "Automatyk10@";

    static Connection conn = null;

    private static DataBaseConnection instance;

    private DataBaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public static synchronized DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }

        return instance;
    }

}
