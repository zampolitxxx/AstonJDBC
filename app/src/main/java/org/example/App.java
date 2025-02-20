package org.example;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:aston_jdbc");

        try (Statement statement = connection.createStatement()) {
            String createTable = """
            CREATE TABLE users (
            id BIGINT PRIMARY KEY AUTO_INCREMENT,
            username VARCHAR(255),
            phone VARCHAR(255))""";
            statement.execute(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Statement statementCreate = connection.createStatement()) {
            String sqlInsert = """
                    INSERT INTO users (username, phone)
                    VALUES ('zampolit', '123456789')
                    """;
            statementCreate.executeUpdate(sqlInsert);
        }

        try (var statementUpdate = connection.createStatement()) {
            String sqlUpdate = """
                    UPDATE users
                    SET phone = 31415926535
                    WHERE username = 'zampolit'
                    """;

            statementUpdate.executeUpdate(sqlUpdate);
        }

        try (Statement statementRead = connection.createStatement()) {
            String sql3 = "SELECT * FROM users";
            ResultSet resultSet = statementRead.executeQuery(sql3);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("phone"));
            }
        }

        try (var statementDrop = connection.createStatement()) {
            String sqlDrop = "DROP TABLE users";
            statementDrop.executeUpdate(sqlDrop);
        }

        connection.close();
    }
}
