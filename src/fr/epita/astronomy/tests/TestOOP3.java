package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.*;
import java.util.ArrayList;
import java.util.List;

public class TestOOP3 {

    public static void main(String[] args) {

        SpaceTelescope hubble = new SpaceTelescope("TEL001", "Hubble Space Telescope",
                "Low Earth Orbit", 2.4, 547.0);

        OpticalTelescope vlt = new OpticalTelescope("TEL002", "Very Large Telescope",
                "Cerro Paranal, Chile", 8.2, "380–750 nm");

        RadioTelescope alma = new RadioTelescope("TEL003", "ALMA",
                "Atacama Desert, Chile", 12.0, 100.0);

        List<Telescope> telescopes = new ArrayList<>();
        telescopes.add(hubble);
        telescopes.add(vlt);
        telescopes.add(alma);

        System.out.println("=== Telescope Polymorphism Demo ===");
        for (Telescope t : telescopes) {
            System.out.println(t);
        }
    }
}