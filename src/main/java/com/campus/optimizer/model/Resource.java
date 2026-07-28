package com.campus.optimizer.model;

/** Mirrors the `resources` table — a vehicle, rider or staff member that can be assigned. */
public class Resource {
    public String resourceId;
    public String resourceType;
    public String homeLocationId;
    public int capacity;
    public String availabilityStatus; // AVAILABLE, BUSY, OFFLINE

    public Resource(String resourceId, String resourceType, String homeLocationId,
                     int capacity, String availabilityStatus) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.homeLocationId = homeLocationId;
        this.capacity = capacity;
        this.availabilityStatus = availabilityStatus;
    }
}
