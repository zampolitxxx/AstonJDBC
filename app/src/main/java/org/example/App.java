package org.example;

import org.example.utils.ConnManager;

import java.sql.Statement;
import java.sql.SQLException;

public class App {
    private static final String CREATE_TABLE = """
            CREATE TABLE users (
            id BIGINT PRIMARY KEY AUTO_INCREMENT,
            username VARCHAR(255),
            phone VARCHAR(255))""";

    private static final String SQL_INSERT = """
            INSERT INTO users (username, phone)
            VALUES ('zampolit', '123456789'),
                   ('other_user', '987654321');
            """;
    private static final String SQL_UPDATE = """
            UPDATE users
            SET phone = 31415926535
            WHERE username = 'zampolit'
            """;
    private static final String SQL_DROP = "DROP TABLE users";
    public static void main(String[] args) throws SQLException {
        var connection = ConnManager.getConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Before INSERT");
        ConnManager.showTable(connection);

        try (Statement statementCreate = connection.createStatement()) {
            statementCreate.executeUpdate(SQL_INSERT);
        }
        System.out.println("\nAfter INSERT");
        ConnManager.showTable(connection);

        try (Statement statementUpdate = connection.createStatement()) {


            statementUpdate.executeUpdate(SQL_UPDATE);
        }
        System.out.println("\nAfter UPDATE");
        ConnManager.showTable(connection);

        try (Statement statementDrop = connection.createStatement()) {
            statementDrop.executeUpdate(SQL_DROP);
        }

        connection.close();
    }


}
