package com.campus.optimizer.datastructures;

/**
 * Custom B-tree (or a documented simulation of database index pages using
 * B-tree structure — either satisfies the brief).
 * Required ops: node split, search.
 * Required evidence: a search trace and an explanation of when/why a node
 * splits as you insert more keys.
 */
public class MyBTree<T extends Comparable<T>> {

    private final int minDegree; // "t" in standard B-tree terminology

    public MyBTree(int minDegree) {
        this.minDegree = minDegree;
        // TODO initialise an empty root node with 0 keys
    }

    public void insert(T value) {
        // TODO if root is full, split it first (tree grows in height here),
        // then insert into the (now non-full) appropriate child.
        throw new UnsupportedOperationException("TODO");
    }

    public boolean search(T value) {
        // TODO walk down the tree, scanning each node's sorted keys to decide
        // which child to descend into; log the path for your search trace.
        throw new UnsupportedOperationException("TODO");
    }

    private void splitChild(/* parent node, index of full child */) {
        // TODO move the full child's median key up into the parent, split its
        // keys/children into two nodes either side of that median.
    }
}
