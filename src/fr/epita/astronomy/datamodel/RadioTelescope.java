package fr.epita.astronomy.datamodel;

public class RadioTelescope extends Telescope {

    private Double frequency;

    public RadioTelescope(String telescopeId, String name, String location,
                          Double apertureDiameter, Double frequency) {
        super(telescopeId, name, location, apertureDiameter);
        this.frequency = frequency;
    }

    public Double getFrequency() { return frequency; }

    @Override
    public String getObservationCapability() {
        return "Detects radio waves at " + frequency + " GHz, allowing deep-space observations.";
    }

    @Override
    public String toString() {
        return "[Radio Telescope] " + super.toString();
    }
}