package fr.epita.astronomy.datamodel;

public class Star {

    private String starId;
    private String name;
    private String constellation;
    private Double apparentMagnitude;
    private Double distanceInLightYears;
    private String spectralType;
    private String discoveryYear;
    private Double mass;

    // Constructor
    public Star(String starId, String name, String constellation, Double apparentMagnitude,
                Double distanceInLightYears, String spectralType, String discoveryYear, Double mass) {
        this.starId = starId;
        this.name = name;
        this.constellation = constellation;
        this.apparentMagnitude = apparentMagnitude;
        this.distanceInLightYears = distanceInLightYears;
        this.spectralType = spectralType;
        this.discoveryYear = discoveryYear;
        this.mass = mass;
    }

    // Getters
    public String getStarId() { return starId; }
    public String getName() { return name; }
    public String getConstellation() { return constellation; }
    public Double getApparentMagnitude() { return apparentMagnitude; }
    public Double getDistanceInLightYears() { return distanceInLightYears; }
    public String getSpectralType() { return spectralType; }
    public String getDiscoveryYear() { return discoveryYear; }
    public Double getMass() { return mass; }

    @Override
    public String toString() {
        return "Star{" +
                "id='" + starId + '\'' +
                ", name='" + name + '\'' +
                ", constellation='" + constellation + '\'' +
                ", apparentMagnitude=" + apparentMagnitude +
                ", distanceInLightYears=" + distanceInLightYears +
                ", spectralType='" + spectralType + '\'' +
                ", discoveryYear='" + discoveryYear + '\'' +
                ", mass=" + mass +
                '}';
    }
}
