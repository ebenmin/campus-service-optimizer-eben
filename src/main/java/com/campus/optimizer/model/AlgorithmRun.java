package com.campus.optimizer.model;

/** Mirrors the `algorithm_runs` table — one row per timed experiment. */
public class AlgorithmRun {
    public String runId;
    public String algorithmName;
    public int inputSize;
    public long timeNs;
    public long memoryKb;
    public String dateRun;

    public AlgorithmRun(String runId, String algorithmName, int inputSize,
                         long timeNs, long memoryKb, String dateRun) {
        this.runId = runId;
        this.algorithmName = algorithmName;
        this.inputSize = inputSize;
        this.timeNs = timeNs;
        this.memoryKb = memoryKb;
        this.dateRun = dateRun;
    }
}
