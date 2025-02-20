package org.example.utils;

import org.example.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public final class ConnManager {

    private static final String DB_URL = "jdbc:h2:mem:aston_jdbc";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static Set<User> getData(Connection connection, String querry) {
        Set<User> users = new HashSet<>();
        try (Statement statementRead = connection.createStatement()) {
            ResultSet resultSet = statementRead.executeQuery(querry);
            while (resultSet.next()) {
                User nu = new User(
                        resultSet.getString("username"),
                        resultSet.getString("phone"));
                users.add(nu);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в методе getData" + e.getMessage());
        }
        return users;
    }
}
