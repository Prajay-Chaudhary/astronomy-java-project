package fr.epita.astronomy.utils;

import fr.epita.astronomy.datamodel.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TelescopeCSVReader {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static List<Telescope> readAll(String filePath) {
        List<Telescope> telescopes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                String[] parts = line.split(";", -1);

                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("\"", "").trim();
                }

                // Parse common fields
                String id = parts[0];
                String name = parts[1];
                String type = parts[2];
                String location = parts[3];
                Double aperture = Double.parseDouble(parts[4]);
                LocalDate date = LocalDate.parse(parts[5], FORMATTER);
                String agency = parts[6];
                Double altitude = Double.parseDouble(parts[7]);

                // Decide which subclass to instantiate based on type
                Telescope telescope;
                if (type.equalsIgnoreCase("Space")) {
                    telescope = new SpaceTelescope(id, name, location, aperture, altitude);
                } else if (type.equalsIgnoreCase("Optical") || type.contains("Optical")) {
                    telescope = new OpticalTelescope(id, name, location, aperture, "380–750 nm");
                } else if (type.equalsIgnoreCase("Radio")) {
                    telescope = new RadioTelescope(id, name, location, aperture, 100.0);
                } else {
                    // Default fallback (optional)
                    telescope = new OpticalTelescope(id, name, location, aperture, "unknown");
                }

                telescopes.add(telescope);
            }

        } catch (IOException e) {
            System.err.println("Error reading telescopes CSV: " + e.getMessage());
        }

        return telescopes;
    }
}