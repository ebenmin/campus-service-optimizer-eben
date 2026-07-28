package com.campus.optimizer.datastructures;

/**
 * Custom binary search tree (unbalanced).
 * Required ops: insert, search, inorder traversal.
 * Required evidence: the search path taken for a lookup, and the sorted
 * output produced by an inorder traversal.
 */
public class MyBST<T extends Comparable<T>> {

    private static class Node<T> {
        T value;
        Node<T> left, right;
        Node(T value) { this.value = value; }
    }

    private Node<T> root;

    public void insert(T value) {
        // TODO standard BST insert: walk left/right by comparison until you
        // hit a null child, attach the new node there.
        throw new UnsupportedOperationException("TODO");
    }

    public boolean search(T value) {
        // TODO walk left/right by comparison; print or log the path taken
        // (that log is your "search path" evidence).
        throw new UnsupportedOperationException("TODO");
    }

    public java.util.List<T> inorder() {
        // TODO left subtree, then this node, then right subtree — should
        // produce values in sorted order.
        throw new UnsupportedOperationException("TODO");
    }
}
