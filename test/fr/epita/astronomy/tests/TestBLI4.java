package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.Telescope;
import fr.epita.astronomy.services.TelescopeService;
import fr.epita.astronomy.services.TelescopeCSVReader;

import java.util.List;
import java.util.Map;

public class TestBLI4 {

    public static void main(String[] args) {

        String telescopeFile = "resources/telescopes.csv";

        // Load telescopes from CSV
        List<Telescope> telescopes = TelescopeCSVReader.readAll(telescopeFile);

        // Create service
        TelescopeService service = new TelescopeService();

        // Compute count by type
        Map<String, Integer> counts = service.computeCountByType(telescopes);

        // Display results
        System.out.println("=== Count of Telescopes per Type ===");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}