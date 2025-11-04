package fr.epita.astronomy.tests;

import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.datamodel.Telescope;
import fr.epita.astronomy.services.StarCSVReader;
import fr.epita.astronomy.services.TelescopeCSVReader;

import java.util.List;

public class TestOOP2 {

    public static void main(String[] args) {

        String telescopeFile = "resources/telescopes.csv";
        String starFile = "resources/stars.csv";

        List<Telescope> telescopes = TelescopeCSVReader.readAll(telescopeFile);
        List<Star> stars = StarCSVReader.readAll(starFile);

        System.out.println("---- Telescopes ----");
        telescopes.forEach(System.out::println);

        System.out.println("\n---- Stars ----");
        stars.forEach(System.out::println);
    }
}
