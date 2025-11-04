package fr.epita.astronomy.dao;

import fr.epita.astronomy.datamodel.Telescope;

import java.sql.*;

public class TelescopeDAO {

    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASSWORD = "pwd";

    // Constructor: creates table if not exists
    public TelescopeDAO() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS TELESCOPES (
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
            System.out.println("✅ TELESCOPES table ready.");

        } catch (SQLException e) {
            System.err.println("❌ Error creating table: " + e.getMessage());
        }
    }

    // Helper method to get a connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE
    public void create(Telescope telescope) {
        String sql = """
            INSERT INTO TELESCOPES (telescopeId, name, type, location, apertureDiameter, operatingAgency, altitude)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, telescope.getTelescopeId());
            ps.setString(2, telescope.getName());
            ps.setString(3, telescope.getClass().getSimpleName());
            ps.setString(4, telescope.getLocation());
            ps.setDouble(5, telescope.getApertureDiameter());
            ps.setString(6, "Unknown Agency");
            ps.setDouble(7, 0.0);

            ps.executeUpdate();
            System.out.println("✅ Inserted telescope: " + telescope.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error inserting telescope: " + e.getMessage());
        }
    }

    // UPDATE (e.g., change location)
    public void update(Telescope telescope) {
        String sql = """
            UPDATE TELESCOPES SET location = ? WHERE telescopeId = ?;
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, telescope.getLocation());
            ps.setString(2, telescope.getTelescopeId());

            int rows = ps.executeUpdate();
            System.out.println("✅ Updated " + rows + " row(s) for telescope " + telescope.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error updating telescope: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(Telescope telescope) {
        String sql = """
            DELETE FROM TELESCOPES WHERE telescopeId = ?;
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, telescope.getTelescopeId());
            int rows = ps.executeUpdate();

            System.out.println("✅ Deleted " + rows + " row(s) for telescope " + telescope.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error deleting telescope: " + e.getMessage());
        }
    }
}
