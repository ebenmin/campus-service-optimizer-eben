package com.campus.optimizer.model;

/** Mirrors the `service_requests` table — a job to be queued/prioritised/dispatched. */
public class ServiceRequest {
    public String requestId;
    public String sourceLocationId;
    public String destinationLocationId;
    public String category;
    public int urgency;            // 1 (low) .. 5 (high)
    public String timeSubmitted;   // ISO-8601 string, e.g. 2026-07-01T08:15
    public String deadline;
    public String status;          // NEW, IN_PROGRESS, DONE, CANCELLED

    public ServiceRequest(String requestId, String sourceLocationId, String destinationLocationId,
                           String category, int urgency, String timeSubmitted, String deadline,
                           String status) {
        this.requestId = requestId;
        this.sourceLocationId = sourceLocationId;
        this.destinationLocationId = destinationLocationId;
        this.category = category;
        this.urgency = urgency;
        this.timeSubmitted = timeSubmitted;
        this.deadline = deadline;
        this.status = status;
    }
}
