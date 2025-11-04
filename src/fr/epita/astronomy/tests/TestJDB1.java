package fr.epita.astronomy.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDB1 {

    // Method to test database table creation
    public static void test() {
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        String user = "user";
        String password = "pwd";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // SQL for creating TELESCOPES table
            String createTableSQL = """
                CREATE TABLE TELESCOPES (
                    telescopeId VARCHAR(255) PRIMARY KEY,
                    name VARCHAR(255),
                    type VARCHAR(255),
                    location VARCHAR(255),
                    apertureDiameter DOUBLE,
                    operatingAgency VARCHAR(255),
                    altitude DOUBLE
                );
                """;

            statement.execute(createTableSQL);
            System.out.println("Table TELESCOPES created successfully in memory.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        test();
    }
}