package fr.epita.astronomy.tests;

import fr.epita.astronomy.dao.TelescopeDAO;
import fr.epita.astronomy.datamodel.OpticalTelescope;
import fr.epita.astronomy.datamodel.RadioTelescope;
import fr.epita.astronomy.datamodel.SpaceTelescope;

public class TestJDB2 {

    public static void main(String[] args) {

        // Initialize DAO (table auto-created)
        TelescopeDAO dao = new TelescopeDAO();

        // Create telescopes
        SpaceTelescope hubble = new SpaceTelescope("TEL001", "Hubble", "Low Earth Orbit", 2.4, 547.0);
        OpticalTelescope vlt = new OpticalTelescope("TEL002", "Very Large Telescope", "Chile", 8.2, "380–750 nm");
        RadioTelescope alma = new RadioTelescope("TEL003", "ALMA", "Atacama", 12.0, 100.0);

        // Create entries in DB
        dao.create(hubble);
        dao.create(vlt);
        dao.create(alma);

        // Update telescope location
        vlt = new OpticalTelescope("TEL002", "Very Large Telescope", "Paranal Observatory, Chile", 8.2, "380–750 nm");
        dao.update(vlt);

        // Delete one telescope
        dao.delete(hubble);
    }
}