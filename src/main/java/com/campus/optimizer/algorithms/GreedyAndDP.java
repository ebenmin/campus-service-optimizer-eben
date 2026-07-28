package com.campus.optimizer.algorithms;

/**
 * Required: at least one greedy algorithm AND one dynamic-programming
 * algorithm, applied to request/resource selection under a budget or
 * capacity constraint (a knapsack-style problem fits naturally).
 * Required evidence: for the greedy algorithm, a COUNTEREXAMPLE input where
 * it produces a worse answer than the DP solution — this is explicitly
 * required by the brief, not optional.
 */
public class GreedyAndDP {

    /** Greedy: e.g. always pick the highest-urgency request that still fits the budget. */
    public static int[] greedySelect(int[] values, int[] costs, int budget) {
        // TODO sort by value/cost ratio (or by urgency) descending, take
        // greedily while budget remains. Document why this can be suboptimal.
        throw new UnsupportedOperationException("TODO");
    }

    /** 0/1 knapsack-style DP: optimal selection under the same budget constraint. */
    public static int knapsackDP(int[] values, int[] costs, int budget) {
        // TODO classic table[i][b] = best value using first i items within
        // budget b. Reconstruct the chosen items from the table afterwards.
        throw new UnsupportedOperationException("TODO");
    }
}
