package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.services.StarService;
import fr.epita.astronomy.services.StarCSVReader;

import java.util.List;
import java.util.Map;

public class TestBLI2 {

    public static void main(String[] args) {

        String starFile = "resources/stars.csv";

        List<Star> stars = StarCSVReader.readAll(starFile);

        // Compute count by constellation
        StarService service = new StarService();
        Map<String, Integer> counts = service.computeCountByConstellation(stars);

        // Display results
        System.out.println("=== Count of Stars per Constellation ===");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
