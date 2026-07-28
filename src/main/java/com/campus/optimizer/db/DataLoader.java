package com.campus.optimizer.db;

import com.campus.optimizer.model.Location;
import com.campus.optimizer.model.Resource;
import com.campus.optimizer.model.Road;
import com.campus.optimizer.model.ServiceRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads seed CSVs from data/ and inserts them into the database. Every
 * loadX method follows the same shape: read CSV -> parse into model objects
 * -> batch-insert into the database -> return the parsed List<T> so it can
 * also be handed straight to your custom data structures without a second
 * database round-trip.
 */
public class DataLoader {

    private final DatabaseManager db;

    public DataLoader(DatabaseManager db) {
        this.db = db;
    }

    public List<Location> loadLocations(Path csvPath) throws IOException, SQLException {
        List<Location> locations = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                Location loc = new Location(
                        parts[0], parts[1], parts[2], parts[3],
                        Double.parseDouble(parts[4]), Double.parseDouble(parts[5])
                );
                locations.add(loc);
            }
        }

        String insertSql = "INSERT OR REPLACE INTO locations " +
                "(location_id, name, area, location_type, x_coord, y_coord) VALUES (?,?,?,?,?,?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            for (Location loc : locations) {
                ps.setString(1, loc.locationId);
                ps.setString(2, loc.name);
                ps.setString(3, loc.area);
                ps.setString(4, loc.locationType);
                ps.setDouble(5, loc.xCoord);
                ps.setDouble(6, loc.yCoord);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return locations;
    }

    public List<Road> loadRoads(Path csvPath) throws IOException, SQLException {
        List<Road> roads = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                roads.add(new Road(
                        p[0], p[1], p[2],
                        Double.parseDouble(p[3]), Double.parseDouble(p[4]), Double.parseDouble(p[5])
                ));
            }
        }

        String insertSql = "INSERT OR REPLACE INTO roads " +
                "(road_id, from_location_id, to_location_id, distance_km, travel_time_min, condition_weight) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            for (Road r : roads) {
                ps.setString(1, r.roadId);
                ps.setString(2, r.fromLocationId);
                ps.setString(3, r.toLocationId);
                ps.setDouble(4, r.distanceKm);
                ps.setDouble(5, r.travelTimeMin);
                ps.setDouble(6, r.conditionWeight);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return roads;
    }

    public List<ServiceRequest> loadServiceRequests(Path csvPath) throws IOException, SQLException {
        List<ServiceRequest> requests = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                requests.add(new ServiceRequest(
                        p[0], p[1], p[2], p[3],
                        Integer.parseInt(p[4]), p[5], p[6], p[7]
                ));
            }
        }

        String insertSql = "INSERT OR REPLACE INTO service_requests " +
                "(request_id, source_location_id, destination_location_id, category, urgency, " +
                "time_submitted, deadline, status) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            for (ServiceRequest r : requests) {
                ps.setString(1, r.requestId);
                ps.setString(2, r.sourceLocationId);
                ps.setString(3, r.destinationLocationId);
                ps.setString(4, r.category);
                ps.setInt(5, r.urgency);
                ps.setString(6, r.timeSubmitted);
                ps.setString(7, r.deadline);
                ps.setString(8, r.status);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return requests;
    }

    public List<Resource> loadResources(Path csvPath) throws IOException, SQLException {
        List<Resource> resources = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                resources.add(new Resource(
                        p[0], p[1], p[2], Integer.parseInt(p[3]), p[4]
                ));
            }
        }

        String insertSql = "INSERT OR REPLACE INTO resources " +
                "(resource_id, resource_type, home_location_id, capacity, availability_status) " +
                "VALUES (?,?,?,?,?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            for (Resource r : resources) {
                ps.setString(1, r.resourceId);
                ps.setString(2, r.resourceType);
                ps.setString(3, r.homeLocationId);
                ps.setInt(4, r.capacity);
                ps.setString(5, r.availabilityStatus);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return resources;
    }

    /** Convenience: loads all four CSVs in the correct order (locations first,
     * since roads/requests/resources reference location IDs as foreign keys). */
    public void loadAll(Path dataDir) throws IOException, SQLException {
        List<Location> locations = loadLocations(dataDir.resolve("locations_full.csv"));
        List<Road> roads = loadRoads(dataDir.resolve("roads_full.csv"));
        List<ServiceRequest> requests = loadServiceRequests(dataDir.resolve("service_requests_full.csv"));
        List<Resource> resources = loadResources(dataDir.resolve("resources_full.csv"));
        System.out.printf("Loaded %d locations, %d roads, %d requests, %d resources.%n",
                locations.size(), roads.size(), requests.size(), resources.size());
    }
}
