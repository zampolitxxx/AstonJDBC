package org.example;

import org.example.utils.ConnManager;

import java.sql.Statement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        var connection = ConnManager.getConnection();

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

        System.out.println("Before INSERT");
        ConnManager.showTable(connection);

        try (Statement statementCreate = connection.createStatement()) {
            String sqlInsert = """
                    INSERT INTO users (username, phone)
                    VALUES ('zampolit', '123456789'),
                           ('other_user', '987654321');
                    """;
            statementCreate.executeUpdate(sqlInsert);
        }

        System.out.println("\nAfter INSERT");
        ConnManager.showTable(connection);

        try (var statementUpdate = connection.createStatement()) {
            String sqlUpdate = """
                    UPDATE users
                    SET phone = 31415926535
                    WHERE username = 'zampolit'
                    """;

            statementUpdate.executeUpdate(sqlUpdate);
        }

        System.out.println("\nAfter UPDATE");
        ConnManager.showTable(connection);

        try (var statementDrop = connection.createStatement()) {
            String sqlDrop = "DROP TABLE users";
            statementDrop.executeUpdate(sqlDrop);
        }

        connection.close();
    }


}
