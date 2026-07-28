package com.campus.optimizer.ui;

import com.campus.optimizer.db.DatabaseManager;
import com.campus.optimizer.db.DataLoader;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Console menu so an examiner can run every demonstration without touching
 * source code (Section 8 requirement). Option 1 (database load) is fully
 * wired up already — options 2-6 call into your datastructures/algorithms/
 * performance packages as each person finishes their piece.
 */
public class ConsoleMenu {

    private static final String DB_PATH = "campus.db";
    private static final Path DATA_DIR = Path.of("data");

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== Campus Service Operations Optimizer ===");
            System.out.println("1. Load data from database");
            System.out.println("2. Dispatch next request (FIFO / urgency / priority)");
            System.out.println("3. Find fastest route between two locations");
            System.out.println("4. Show reachable locations from a dispatch point");
            System.out.println("5. Run budget-constrained request selection (greedy vs DP)");
            System.out.println("6. Run performance experiments");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    loadData();
                    break;
                case "2":
                    System.out.println("TODO: call your queue/heap dispatch logic.");
                    break;
                case "3":
                    System.out.println("TODO: call GraphAlgorithms.dijkstra, print path.");
                    break;
                case "4":
                    System.out.println("TODO: call GraphAlgorithms.bfs or dfs, print result.");
                    break;
                case "5":
                    System.out.println("TODO: call GreedyAndDP, print both results side by side.");
                    break;
                case "6":
                    System.out.println("TODO: call ExperimentRunner.measure for each algorithm.");
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Not a valid option, try again.");
            }
        }
        System.out.println("Goodbye.");
    }

    private void loadData() {
        try {
            DatabaseManager db = new DatabaseManager(DB_PATH);
            db.applySchema(Path.of("schema.sql"));
            new DataLoader(db).loadAll(DATA_DIR);
        } catch (Exception e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
    }
}
