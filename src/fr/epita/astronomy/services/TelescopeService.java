package fr.epita.astronomy.services;

import fr.epita.astronomy.datamodel.Telescope;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TelescopeService {

    /**
     * Compute how many telescopes exist per type.
     *
     * @param telescopes list of Telescope objects
     * @return a Map with type as key and count as value
     */
    public Map<String, Integer> computeCountByType(List<Telescope> telescopes) {
        Map<String, Integer> result = new HashMap<>();

        for (Telescope t : telescopes) {
            String typeName = t.getClass().getSimpleName(); // e.g. SpaceTelescope
            result.put(typeName, result.getOrDefault(typeName, 0) + 1);
        }

        return result;
    }

    /**
     * Find all telescopes with aperture diameter greater than a given value.
     *
     * @param telescopes List of Telescope objects
     * @param minAperture minimum aperture diameter to filter by
     * @return List of telescopes with aperture > minAperture
     */
    public List<Telescope> findTelescopesWithApertureGreaterThan(List<Telescope> telescopes, double minAperture) {
        List<Telescope> result = new ArrayList<>();

        for (Telescope t : telescopes) {
            if (t.getApertureDiameter() != null && t.getApertureDiameter() > minAperture) {
                result.add(t);
            }
        }

        return result;
    }
}