-- Ghana Smart Service Operations Optimizer — Campus Edition
-- SQLite schema. Run once: sqlite3 campus.db < schema.sql

CREATE TABLE IF NOT EXISTS locations (
    location_id     TEXT PRIMARY KEY,
    name            TEXT NOT NULL,
    area            TEXT NOT NULL,
    location_type   TEXT NOT NULL,
    x_coord         REAL,
    y_coord         REAL
);

CREATE TABLE IF NOT EXISTS roads (
    road_id            TEXT PRIMARY KEY,
    from_location_id   TEXT NOT NULL REFERENCES locations(location_id),
    to_location_id     TEXT NOT NULL REFERENCES locations(location_id),
    distance_km        REAL NOT NULL,
    travel_time_min    REAL NOT NULL,
    condition_weight   REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS service_requests (
    request_id            TEXT PRIMARY KEY,
    source_location_id    TEXT NOT NULL REFERENCES locations(location_id),
    destination_location_id TEXT NOT NULL REFERENCES locations(location_id),
    category              TEXT NOT NULL,
    urgency               INTEGER NOT NULL,
    time_submitted        TEXT NOT NULL,
    deadline              TEXT NOT NULL,
    status                TEXT NOT NULL DEFAULT 'NEW'
);

CREATE TABLE IF NOT EXISTS resources (
    resource_id           TEXT PRIMARY KEY,
    resource_type         TEXT NOT NULL,
    home_location_id      TEXT NOT NULL REFERENCES locations(location_id),
    capacity              INTEGER NOT NULL,
    availability_status   TEXT NOT NULL DEFAULT 'AVAILABLE'
);

CREATE TABLE IF NOT EXISTS algorithm_runs (
    run_id          TEXT PRIMARY KEY,
    algorithm_name  TEXT NOT NULL,
    input_size      INTEGER NOT NULL,
    time_ns         INTEGER NOT NULL,
    memory_kb       INTEGER,
    date_run        TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS audit_events (
    event_id     INTEGER PRIMARY KEY AUTOINCREMENT,
    event_type   TEXT NOT NULL,
    description  TEXT NOT NULL,
    event_time   TEXT NOT NULL
);
