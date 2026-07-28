package com.campus.optimizer.algorithms;

import com.campus.optimizer.datastructures.MyGraph;
import com.campus.optimizer.datastructures.MyDisjointSet;
import java.util.List;

/**
 * Required: BFS, DFS, Dijkstra, Prim, Kruskal — all operating on MyGraph.
 * Required evidence: BFS/DFS traversal order + diagram; Dijkstra distance and
 * predecessor table for a chosen source; MST edge list and total cost for
 * both Prim and Kruskal (they should agree on total cost).
 */
public class GraphAlgorithms {

    public static List<Integer> bfs(MyGraph graph, int startVertex) {
        // TODO use your own MyQueue, not java.util.ArrayDeque, to hold the
        // frontier; use your own MySetMap or a boolean[] to track visited.
        throw new UnsupportedOperationException("TODO");
    }

    public static List<Integer> dfs(MyGraph graph, int startVertex) {
        // TODO recursive or use your own MyStack for the explicit-stack version
        throw new UnsupportedOperationException("TODO");
    }

    public static int[] dijkstra(MyGraph graph, int sourceVertex) {
        // TODO use your own MyHeap as the priority queue of (vertex, distance).
        // Return the distance array; also track predecessors for path reconstruction.
        throw new UnsupportedOperationException("TODO");
    }

    public static List<int[]> primMST(MyGraph graph) {
        // TODO grow a tree from an arbitrary start vertex, always adding the
        // cheapest edge that connects a new vertex, using your own MyHeap.
        throw new UnsupportedOperationException("TODO");
    }

    public static List<int[]> kruskalMST(MyGraph graph, List<int[]> allEdges) {
        // TODO sort edges by weight (reuse SortAlgorithms), then use
        // MyDisjointSet.union to skip edges that would create a cycle.
        throw new UnsupportedOperationException("TODO");
    }
}
