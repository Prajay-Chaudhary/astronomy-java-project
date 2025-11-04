package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.services.StarService;
import fr.epita.astronomy.utils.StarCSVReader;

import java.util.List;
import java.util.Map;

public class TestBLI3 {

    public static void main(String[] args) {

        String starFile = "resources/stars.csv";

        List<Star> stars = StarCSVReader.readAll(starFile);
        StarService service = new StarService();

        // Compute average distance by spectral class
        Map<String, Double> averages = service.computeAverageDistanceBySpectralClass(stars);

        // Display results formatted to 2 decimals
        System.out.println("=== Average Distance (ly) by Spectral Class ===");
        for (Map.Entry<String, Double> entry : averages.entrySet()) {
            System.out.printf("%s : %.2f%n", entry.getKey(), entry.getValue());
        }
    }
}
