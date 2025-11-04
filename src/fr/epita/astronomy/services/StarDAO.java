package fr.epita.astronomy.services;

import fr.epita.astronomy.datamodel.Star;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class StarDAO {

    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASSWORD = "pwd";

    // Constructor: create table if not exists
    public StarDAO() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS STARS (
                    starId VARCHAR(255) PRIMARY KEY,
                    name VARCHAR(255),
                    constellation VARCHAR(255),
                    apparentMagnitude DOUBLE,
                    distanceInLightYears DOUBLE,
                    spectralType VARCHAR(50),
                    discoveryYear VARCHAR(50),
                    mass DOUBLE
                );
                """;

            statement.execute(createTableSQL);
            System.out.println("✅ STARS table ready.");

        } catch (SQLException e) {
            System.err.println("❌ Error creating STARS table: " + e.getMessage());
        }
    }

    // Helper connection method
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE
    public void create(Star star) {
        String sql = """
            INSERT INTO STARS (starId, name, constellation, apparentMagnitude,
                               distanceInLightYears, spectralType, discoveryYear, mass)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, star.getStarId());
            ps.setString(2, star.getName());
            ps.setString(3, star.getConstellation());
            ps.setDouble(4, star.getApparentMagnitude());
            ps.setDouble(5, star.getDistanceInLightYears());
            ps.setString(6, star.getSpectralType());
            ps.setString(7, star.getDiscoveryYear());
            ps.setDouble(8, star.getMass());

            ps.executeUpdate();
            System.out.println("✅ Inserted star: " + star.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error inserting star: " + e.getMessage());
        }
    }

    // UPDATE (update constellation by ID)
    public void update(Star star) {
        String sql = """
            UPDATE STARS SET constellation = ? WHERE starId = ?;
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, star.getConstellation());
            ps.setString(2, star.getStarId());

            int rows = ps.executeUpdate();
            System.out.println("✅ Updated " + rows + " row(s) for star " + star.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error updating star: " + e.getMessage());
        }
    }

    // DELETE
    public void delete(Star star) {
        String sql = "DELETE FROM STARS WHERE starId = ?;";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, star.getStarId());
            int rows = ps.executeUpdate();
            System.out.println("✅ Deleted " + rows + " row(s) for star " + star.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error deleting star: " + e.getMessage());
        }
    }

    // BONUS: batch insert from list
    public void saveAll(List<Star> stars) {
        for (Star s : stars) {
            create(s);
        }
    }

    /**
     * Search for stars by constellation name (case-insensitive).
     *
     * @param constellation constellation to search
     * @return list of stars in that constellation
     */
    public List<Star> searchByConstellation(String constellation) {
        List<Star> results = new ArrayList<>();

        String sql = "SELECT * FROM STARS WHERE LOWER(constellation) = LOWER(?);";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, constellation);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Star star = new Star(
                        rs.getString("starId"),
                        rs.getString("name"),
                        rs.getString("constellation"),
                        rs.getDouble("apparentMagnitude"),
                        rs.getDouble("distanceInLightYears"),
                        rs.getString("spectralType"),
                        rs.getString("discoveryYear"),
                        rs.getDouble("mass")
                );
                results.add(star);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error searching stars: " + e.getMessage());
        }

        return results;
    }
}