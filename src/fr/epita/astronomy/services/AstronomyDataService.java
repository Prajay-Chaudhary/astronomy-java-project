package fr.epita.astronomy.services;

import fr.epita.astronomy.datamodel.projections.ObservationProjection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AstronomyDataService {

    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASSWORD = "pwd";

    // Ensure OBSERVATIONS table exists
    public AstronomyDataService() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            String sql = """
                CREATE TABLE IF NOT EXISTS OBSERVATIONS (
                    observationId INT PRIMARY KEY,
                    telescopeId VARCHAR(255),
                    starId VARCHAR(255),
                    observationDate DATE,
                    duration INT,
                    quality VARCHAR(50),
                    FOREIGN KEY (telescopeId) REFERENCES TELESCOPES(telescopeId),
                    FOREIGN KEY (starId) REFERENCES STARS(starId)
                );
                """;
            stmt.execute(sql);
            System.out.println("✅ OBSERVATIONS table ready.");

        } catch (SQLException e) {
            System.err.println("❌ Error creating OBSERVATIONS table: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Insert example observation data
    public void insertSampleObservations() {
        String sql = """
            INSERT INTO OBSERVATIONS (observationId, telescopeId, starId, observationDate, duration, quality)
            VALUES (?, ?, ?, ?, ?, ?);
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Sample data (replace with your IDs)
            ps.setInt(1, 1);
            ps.setString(2, "TEL002");
            ps.setString(3, "S001");
            ps.setDate(4, Date.valueOf(LocalDate.of(2024, 6, 15)));
            ps.setInt(5, 120);
            ps.setString(6, "Excellent");
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setString(2, "TEL003");
            ps.setString(3, "S002");
            ps.setDate(4, Date.valueOf(LocalDate.of(2023, 11, 2)));
            ps.setInt(5, 90);
            ps.setString(6, "Good");
            ps.executeUpdate();

            ps.setInt(1, 3);
            ps.setString(2, "TEL002");
            ps.setString(3, "S003");
            ps.setDate(4, Date.valueOf(LocalDate.of(2025, 2, 12)));
            ps.setInt(5, 150);
            ps.setString(6, "Excellent");
            ps.executeUpdate();

            System.out.println("✅ Sample observations inserted.");

        } catch (SQLException e) {
            System.err.println("❌ Error inserting sample observations: " + e.getMessage());
        }
    }

    // JOIN query: combine all tables into projections
    public List<ObservationProjection> getObservationsWithDetails() {
        List<ObservationProjection> results = new ArrayList<>();

        String sql = """
            SELECT o.observationId, t.name AS telescopeName, s.name AS starName,
                   s.constellation, o.observationDate, o.duration, o.quality
            FROM OBSERVATIONS o
            JOIN TELESCOPES t ON o.telescopeId = t.telescopeId
            JOIN STARS s ON o.starId = s.starId
            ORDER BY o.observationDate DESC;
            """;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ObservationProjection op = new ObservationProjection(
                        rs.getInt("observationId"),
                        rs.getString("telescopeName"),
                        rs.getString("starName"),
                        rs.getString("constellation"),
                        rs.getDate("observationDate").toLocalDate(),
                        rs.getInt("duration"),
                        rs.getString("quality")
                );
                results.add(op);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error during JOIN query: " + e.getMessage());
        }

        return results;
    }
}
