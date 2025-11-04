package fr.epita.astronomy.datamodel;

public class OpticalTelescope extends Telescope {

    private String wavelengthRange;

    public OpticalTelescope(String telescopeId, String name, String location,
                            Double apertureDiameter, String wavelengthRange) {
        super(telescopeId, name, location, apertureDiameter);
        this.wavelengthRange = wavelengthRange;
    }

    public String getWavelengthRange() { return wavelengthRange; }

    @Override
    public String getObservationCapability() {
        return "Observes in visible light within range " + wavelengthRange + ".";
    }

    @Override
    public String toString() {
        return "[Optical Telescope] " + super.toString();
    }
}