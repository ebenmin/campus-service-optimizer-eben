package com.campus.optimizer.datastructures;

/**
 * Custom disjoint-set / union-find, backed by an int array (works directly
 * with location indices 0..n-1).
 * Required ops: makeSet, find (with path compression), union (by rank or size).
 * Required evidence: a connectivity trace showing which locations merge into
 * the same set as you run Kruskal's algorithm.
 */
public class MyDisjointSet {

    private final int[] parent;
    private final int[] rank;

    public MyDisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            makeSet(i);
        }
    }

    public void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }

    public int find(int i) {
        // TODO path compression: parent[i] = find(parent[i]); return parent[i];
        throw new UnsupportedOperationException("TODO");
    }

    public boolean union(int a, int b) {
        // TODO find roots of a and b; if equal, return false (already connected,
        // adding this edge would create a cycle — this IS the Kruskal check).
        // Otherwise attach the lower-rank root under the higher-rank root
        // (union by rank) and return true.
        throw new UnsupportedOperationException("TODO");
    }
}
