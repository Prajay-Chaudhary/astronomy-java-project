package fr.epita.astronomy.tests;

import fr.epita.astronomy.dao.StarDAO;
import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.utils.StarCSVReader;

import java.util.List;

public class TestJDB4 {

    public static void main(String[] args) {

        String starFile = "resources/stars.csv";

        // Load all stars from CSV
        List<Star> stars = StarCSVReader.readAll(starFile);

        // Create DAO and initialize table
        StarDAO dao = new StarDAO();

        // Save all stars to DB
        dao.saveAll(stars);
        System.out.println("✅ All stars saved to the database.");

        // Search for stars in a specific constellation
        String searchConstellation = "Orion";  // you can change this
        System.out.println("\n=== Searching for stars in constellation: " + searchConstellation + " ===");

        List<Star> foundStars = dao.searchByConstellation(searchConstellation);

        if (foundStars.isEmpty()) {
            System.out.println("No stars found in constellation " + searchConstellation);
        } else {
            for (Star s : foundStars) {
                System.out.printf("%-10s | %-20s | %-15s | %.2f ly | %s%n",
                        s.getStarId(), s.getName(), s.getSpectralType(),
                        s.getDistanceInLightYears(), s.getDiscoveryYear());
            }
        }
    }
}