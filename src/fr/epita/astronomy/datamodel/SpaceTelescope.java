package fr.epita.astronomy.datamodel;

public class SpaceTelescope extends Telescope {

    private Double orbitAltitude;

    public SpaceTelescope(String telescopeId, String name, String location,
                          Double apertureDiameter, Double orbitAltitude) {
        super(telescopeId, name, location, apertureDiameter);
        this.orbitAltitude = orbitAltitude;
    }

    public Double getOrbitAltitude() { return orbitAltitude; }

    @Override
    public String getObservationCapability() {
        return "Operates above Earth’s atmosphere, free from atmospheric distortion. " +
                "Orbit Altitude: " + orbitAltitude + " km.";
    }

    @Override
    public String toString() {
        return "[Space Telescope] " + super.toString();
    }
}