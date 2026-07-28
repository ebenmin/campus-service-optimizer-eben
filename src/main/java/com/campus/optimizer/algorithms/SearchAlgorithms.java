package com.campus.optimizer.algorithms;

/**
 * Required: linear search AND binary search, both hand-written.
 * Required evidence: a trace table for binary search (low/mid/high at each
 * step) and an explicit statement that binary search requires sorted input
 * (test what happens if you call it on unsorted data — that's your invalid
 * precondition test case).
 */
public class SearchAlgorithms {

    public static int linearSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] sortedData, int target) {
        // TODO precondition: sortedData must be sorted ascending.
        // int low = 0, high = sortedData.length - 1;
        // while (low <= high) { mid = ...; compare; narrow low/high }
        throw new UnsupportedOperationException("TODO");
    }
}
