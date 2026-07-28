package com.campus.optimizer.model;

/** Mirrors the `locations` table. Plain data holder — no logic here. */
public class Location {
    public String locationId;
    public String name;
    public String area;
    public String locationType;
    public double xCoord;
    public double yCoord;

    public Location(String locationId, String name, String area, String locationType,
                     double xCoord, double yCoord) {
        this.locationId = locationId;
        this.name = name;
        this.area = area;
        this.locationType = locationType;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {
        return locationId + " (" + name + ", " + area + ")";
    }
}
