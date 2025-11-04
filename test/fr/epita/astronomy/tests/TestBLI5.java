package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.Telescope;
import fr.epita.astronomy.services.TelescopeService;
import fr.epita.astronomy.services.TelescopeCSVReader;

import java.util.List;

public class TestBLI5 {

    public static void main(String[] args) {

        String telescopeFile = "resources/telescopes.csv";

        List<Telescope> telescopes = TelescopeCSVReader.readAll(telescopeFile);
        TelescopeService service = new TelescopeService();

        // Filter telescopes with aperture > 5.0 meters
        double minAperture = 5.0;
        List<Telescope> filteredTelescopes = service.findTelescopesWithApertureGreaterThan(telescopes, minAperture);

        // Display results
        System.out.println("=== Telescopes with Aperture > " + minAperture + "m ===");
        if (filteredTelescopes.isEmpty()) {
            System.out.println("No telescopes found with aperture greater than " + minAperture + "m.");
        } else {
            for (Telescope t : filteredTelescopes) {
                System.out.printf("%-25s | %-20s | %.2f m%n",
                        t.getName(),
                        t.getClass().getSimpleName(),
                        t.getApertureDiameter());
            }
        }
    }
}
