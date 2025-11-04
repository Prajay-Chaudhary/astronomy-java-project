package fr.epita.astronomy.datamodel;

public abstract class Telescope {

    protected String telescopeId;
    protected String name;
    protected String location;
    protected Double apertureDiameter;

    // Constructor
    public Telescope(String telescopeId, String name, String location, Double apertureDiameter) {
        this.telescopeId = telescopeId;
        this.name = name;
        this.location = location;
        this.apertureDiameter = apertureDiameter;
    }

    // Abstract method
    public abstract String getObservationCapability();

    // Concrete method
    public String getDescription() {
        return "Telescope ID: " + telescopeId +
                ", Name: " + name +
                ", Location: " + location +
                ", Aperture Diameter: " + apertureDiameter + "m";
    }

    // Getters
    public String getTelescopeId() { return telescopeId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public Double getApertureDiameter() { return apertureDiameter; }

    @Override
    public String toString() {
        return getDescription() + " | Capability: " + getObservationCapability();
    }
}