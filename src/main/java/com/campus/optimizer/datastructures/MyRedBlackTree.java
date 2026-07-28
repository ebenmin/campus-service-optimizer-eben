package com.campus.optimizer.datastructures;

/**
 * Custom red-black tree (or a clearly-documented simplified self-balancing
 * tree, e.g. an AVL tree — either satisfies the brief as long as you explain
 * the balancing rule).
 * Required ops: insertion with rotation/recolouring.
 * Required evidence: before/after rotation diagrams, and a discussion of how
 * tree height stays O(log n) compared to the plain MyBST.
 */
public class MyRedBlackTree<T extends Comparable<T>> {

    private enum Color { RED, BLACK }

    private static class Node<T> {
        T value;
        Node<T> left, right, parent;
        Color color = Color.RED;
        Node(T value) { this.value = value; }
    }

    private Node<T> root;

    public void insert(T value) {
        // TODO 1. standard BST insert, new node coloured RED
        //      2. fix-up: rotate/recolour walking up from the new node while
        //         its parent is RED, per the standard red-black insert cases.
        throw new UnsupportedOperationException("TODO");
    }

    private void rotateLeft(Node<T> node) {
        // TODO
    }

    private void rotateRight(Node<T> node) {
        // TODO
    }

    public int height() {
        // TODO used to compare against MyBST.height() in your report
        throw new UnsupportedOperationException("TODO");
    }
}
