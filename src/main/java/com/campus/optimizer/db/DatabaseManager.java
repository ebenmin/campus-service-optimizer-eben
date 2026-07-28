package com.campus.optimizer.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Opens the SQLite connection and applies schema.sql on first run.
 * JDBC/DriverManager is an allowed built-in utility (Section 8 of the brief) —
 * only the core data-structure logic must be hand-built, not database plumbing.
 */
public class DatabaseManager {

    private final String dbPath;

    public DatabaseManager(String dbPath) {
        this.dbPath = dbPath;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    /** Creates all tables from schema.sql if they don't already exist. */
    public void applySchema(Path schemaSqlPath) throws SQLException, IOException {
        String sql = Files.readString(schemaSqlPath);
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            for (String statement : sql.split(";")) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
        }
    }
}
