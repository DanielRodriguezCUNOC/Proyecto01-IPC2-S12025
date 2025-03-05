package com.happycomputer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectDB {

    private static final String URL = "jdbc:mysql://localhost:3306/happycomputer?useSSL=false&serverTimezone=UTC";
    private static final String USER = "ipc2_user";
    private static final String PASSWORD = "proyectos";

    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver JDBC", e);
        }
    }

    public ConectDB() {
    }
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (ConectDB.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                }
            }
        }
        return connection;
    }
}
