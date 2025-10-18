package ru.itis.kpfu.sorokin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "qwerty007"
                );
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException();
            }
        }

        return connection;
    }
}
