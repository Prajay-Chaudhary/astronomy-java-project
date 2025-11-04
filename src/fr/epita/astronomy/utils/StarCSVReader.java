package fr.epita.astronomy.utils;

import fr.epita.astronomy.datamodel.Star;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StarCSVReader {

    public static List<Star> readAll(String filePath) {
        List<Star> stars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // handle quoted commas
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("\"", "").trim();
                }

                Star s = new Star(
                        parts[0],
                        parts[1],
                        parts[2],
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4]),
                        parts[5],
                        parts[6],
                        Double.parseDouble(parts[7])
                );
                stars.add(s);
            }

        } catch (IOException e) {
            System.err.println("Error reading stars CSV: " + e.getMessage());
        }

        return stars;
    }
}
