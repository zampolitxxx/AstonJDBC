package org.example.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public final class ConnManager {
    public static Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:h2:mem:aston_jdbc";
        return DriverManager.getConnection(DB_URL);
    }

    public static void showTable(Connection connection) {
        try (Statement statementRead = connection.createStatement()) {
            String sql3 = "SELECT * FROM users";
            ResultSet resultSet = statementRead.executeQuery(sql3);
            while (resultSet.next()) {
                System.out.println("username is: " +
                        resultSet.getString("username") +
                        "; phone number is: " +
                                resultSet.getString("phone")
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в методе showTable" + e.getMessage());
        }
    }
}
