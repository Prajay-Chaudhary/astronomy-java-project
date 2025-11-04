package fr.epita.astronomy.tests;

import fr.epita.astronomy.dao.StarDAO;
import fr.epita.astronomy.dao.TelescopeDAO;
import fr.epita.astronomy.datamodel.OpticalTelescope;
import fr.epita.astronomy.datamodel.RadioTelescope;
import fr.epita.astronomy.datamodel.SpaceTelescope;
import fr.epita.astronomy.datamodel.Star;
import fr.epita.astronomy.datamodel.projections.ObservationProjection;
import fr.epita.astronomy.services.AstronomyDataService;
import fr.epita.astronomy.utils.StarCSVReader;

import java.util.List;

public class TestJDB5 {

    public static void main(String[] args) {

        // 1️⃣ Initialize DAO classes (create tables)
        TelescopeDAO telescopeDAO = new TelescopeDAO();
        StarDAO starDAO = new StarDAO();
        AstronomyDataService dataService = new AstronomyDataService();

        // 2️⃣ Create telescope records
        SpaceTelescope hubble = new SpaceTelescope("TEL001", "Hubble", "Orbit", 2.4, 547.0);
        OpticalTelescope vlt = new OpticalTelescope("TEL002", "VLT", "Chile", 8.2, "380–750 nm");
        RadioTelescope alma = new RadioTelescope("TEL003", "ALMA", "Atacama", 12.0, 100.0);

        telescopeDAO.create(hubble);
        telescopeDAO.create(vlt);
        telescopeDAO.create(alma);

        // 3️⃣ Insert stars from CSV
        List<Star> stars = StarCSVReader.readAll("resources/stars.csv");
        starDAO.saveAll(stars);

        // 4️⃣ Insert sample observations
        dataService.insertSampleObservations();

        // 5️⃣ Retrieve and display observations
        System.out.println("\n=== Observations (sorted by date DESC) ===");
        List<ObservationProjection> observations = dataService.getObservationsWithDetails();

        for (ObservationProjection op : observations) {
            System.out.println(op);
        }
    }
}