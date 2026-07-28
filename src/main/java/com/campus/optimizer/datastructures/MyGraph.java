package com.campus.optimizer.datastructures;

import java.util.List;

/**
 * Custom graph representation — implement BOTH an adjacency list and an
 * adjacency matrix (the brief requires both), backed by campus locations
 * and roads.
 * Required ops/algorithms built on top of this: BFS, DFS, Dijkstra, Prim,
 * Kruskal — put those in the algorithms/ package, operating on this class.
 * Required evidence: BFS/DFS traversal order, Dijkstra distance + predecessor
 * table, MST edge list and total cost.
 */
public class MyGraph {

    private final int numVertices;
    private final List<int[]>[] adjacencyList; // each entry: {neighbourIndex, weight}
    private final int[][] adjacencyMatrix;      // 0 or NO_EDGE if not connected

    public static final int NO_EDGE = -1;

    @SuppressWarnings("unchecked")
    public MyGraph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new List[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new java.util.ArrayList<>();
        }
        adjacencyMatrix = new int[numVertices][numVertices];
        for (int[] row : adjacencyMatrix) {
            java.util.Arrays.fill(row, NO_EDGE);
        }
    }

    public void addEdge(int from, int to, int weight) {
        // TODO add to both representations; if the campus road is two-way,
        // add the reverse edge too (document your choice either way).
        throw new UnsupportedOperationException("TODO");
    }

    public List<int[]> neighbours(int vertex) {
        return adjacencyList[vertex];
    }

    public int weight(int from, int to) {
        return adjacencyMatrix[from][to];
    }

    public int size() {
        return numVertices;
    }
}
