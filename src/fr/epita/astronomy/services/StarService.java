package fr.epita.astronomy.services;

import fr.epita.astronomy.datamodel.Star;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarService {

    /**
     * Compute the absolute magnitude of a star.
     *
     * Formula (simplified):
     *   M = m - 5 * (log10(d) - 1)
     * where:
     *   M = absolute magnitude
     *   m = apparent magnitude
     *   d = distance in light-years (we convert to parsecs by dividing by 3.26)
     */
    public double computeAbsoluteMagnitude(Star star) {
        double apparentMagnitude = star.getApparentMagnitude();
        double distanceInParsecs = star.getDistanceInLightYears() / 3.26;
        return apparentMagnitude - 5 * (Math.log10(distanceInParsecs) - 1);
    }

    /**
     * Compute the number of stars per constellation.
     *
     * @param stars List of Star objects
     * @return Map where key = constellation name, value = count of stars
     */
    public Map<String, Integer> computeCountByConstellation(List<Star> stars) {
        Map<String, Integer> result = new HashMap<>();

        for (Star s : stars) {
            String constellation = s.getConstellation();

            // If constellation already in map, increment count; otherwise start at 1
            result.put(constellation, result.getOrDefault(constellation, 0) + 1);
        }

        return result;
    }

    /**
     * Compute the average distance by spectral class.
     *
     * Example result:
     * { "O" : 820.0, "B" : 608.0, "A" : 201.5, "G" : 23.5 }
     */
    public Map<String, Double> computeAverageDistanceBySpectralClass(List<Star> stars) {
        Map<String, Double> totalDistance = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();

        for (Star s : stars) {
            if (s.getSpectralType() == null || s.getSpectralType().isEmpty()) continue;

            // Extract the first letter as spectral class
            String spectralClass = s.getSpectralType().substring(0, 1).toUpperCase();

            totalDistance.put(spectralClass,
                    totalDistance.getOrDefault(spectralClass, 0.0) + s.getDistanceInLightYears());

            counts.put(spectralClass, counts.getOrDefault(spectralClass, 0) + 1);
        }

        // Compute averages
        Map<String, Double> averageDistance = new HashMap<>();
        for (String key : totalDistance.keySet()) {
            double avg = totalDistance.get(key) / counts.get(key);
            averageDistance.put(key, avg);
        }

        return averageDistance;
    }
}
