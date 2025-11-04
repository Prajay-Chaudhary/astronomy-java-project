package fr.epita.astronomy.datamodel.projections;

import java.time.LocalDate;

public class ObservationProjection {

    private Integer observationId;
    private String telescopeName;
    private String starName;
    private String constellation;
    private LocalDate observationDate;
    private Integer duration;
    private String quality;

    public ObservationProjection(Integer observationId, String telescopeName, String starName,
                                 String constellation, LocalDate observationDate,
                                 Integer duration, String quality) {
        this.observationId = observationId;
        this.telescopeName = telescopeName;
        this.starName = starName;
        this.constellation = constellation;
        this.observationDate = observationDate;
        this.duration = duration;
        this.quality = quality;
    }

    public Integer getObservationId() { return observationId; }
    public String getTelescopeName() { return telescopeName; }
    public String getStarName() { return starName; }
    public String getConstellation() { return constellation; }
    public LocalDate getObservationDate() { return observationDate; }
    public Integer getDuration() { return duration; }
    public String getQuality() { return quality; }

    @Override
    public String toString() {
        return String.format(
                "%-3d | %-20s | %-15s | %-12s | %s | %3d min | %s",
                observationId, telescopeName, starName, constellation,
                observationDate, duration, quality
        );
    }
}
