package fr.epita.astronomy.tests;

import fr.epita.astronomy.dao.StarDAO;
import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.utils.StarCSVReader;

import java.util.List;

public class TestJDB3 {

    public static void main(String[] args) {

        String starFile = "resources/stars.csv";

        List<Star> stars = StarCSVReader.readAll(starFile);

        StarDAO dao = new StarDAO();
        dao.saveAll(stars);

        // Optional: test update and delete
        if (!stars.isEmpty()) {
            Star firstStar = stars.get(0);
            System.out.println("\n--- Testing update ---");
            firstStar = new Star(firstStar.getStarId(), firstStar.getName(),
                    "Updated Constellation", firstStar.getApparentMagnitude(),
                    firstStar.getDistanceInLightYears(), firstStar.getSpectralType(),
                    firstStar.getDiscoveryYear(), firstStar.getMass());
            dao.update(firstStar);

            System.out.println("\n--- Testing delete ---");
            dao.delete(firstStar);
        }

        System.out.println("\n✅ All stars saved and tested successfully.");
    }
}