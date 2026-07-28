package com.campus.optimizer.performance;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Times algorithms at increasing input sizes and writes results to
 * results/performance/*.csv for plotting (Excel, Python matplotlib, etc.).
 * Required: run each experiment at least 3 times and report the average
 * (Section 9 of the brief) — do that averaging here, not by hand.
 */
public class ExperimentRunner {

    public interface TimedTask {
        void run(int inputSize);
    }

    /** Runs `task` 3 times at each size in `inputSizes`, averages, writes a CSV row per size. */
    public static void measure(String algorithmName, int[] inputSizes, TimedTask task,
                                String outputCsvPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputCsvPath)) {
            writer.write("algorithm,input_size,avg_time_ns\n");
            for (int n : inputSizes) {
                long total = 0;
                for (int trial = 0; trial < 3; trial++) {
                    long start = System.nanoTime();
                    task.run(n);
                    long end = System.nanoTime();
                    total += (end - start);
                }
                long avg = total / 3;
                writer.write(algorithmName + "," + n + "," + avg + "\n");
            }
        }
    }
}
