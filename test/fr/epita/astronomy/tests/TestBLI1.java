package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.services.StarService;
import fr.epita.astronomy.services.StarCSVReader;

import java.util.List;

public class TestBLI1 {

    public static void main(String[] args) {

        String starFile = "resources/stars.csv";
        List<Star> stars = StarCSVReader.readAll(starFile);

        // Safety check
        if (stars.size() < 3) {
            System.out.println("Not enough stars in dataset to pick the 3rd one.");
            return;
        }

        // Pick the 3rd star (index 2)
        Star thirdStar = stars.get(2);

        // Compute absolute magnitude
        StarService service = new StarService();
        double absoluteMag = service.computeAbsoluteMagnitude(thirdStar);

        System.out.printf("Absolute Magnitude of %s: %.2f%n", thirdStar.getName(), absoluteMag);
    }
}
