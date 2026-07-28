package com.campus.optimizer.model;

/** Mirrors the `roads` table — a weighted edge between two locations. */
public class Road {
    public String roadId;
    public String fromLocationId;
    public String toLocationId;
    public double distanceKm;
    public double travelTimeMin;
    public double conditionWeight;

    public Road(String roadId, String fromLocationId, String toLocationId,
                double distanceKm, double travelTimeMin, double conditionWeight) {
        this.roadId = roadId;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distanceKm = distanceKm;
        this.travelTimeMin = travelTimeMin;
        this.conditionWeight = conditionWeight;
    }
}
