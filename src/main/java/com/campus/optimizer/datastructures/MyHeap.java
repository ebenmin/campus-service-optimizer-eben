package com.campus.optimizer.datastructures;

/**
 * Custom binary heap (min-heap or max-heap — pick based on your dispatch
 * rule, e.g. min-heap on urgency so the most urgent request is extracted first).
 * Required ops: insert, extractMin/extractMax, heapify.
 * Required evidence: a dispatch-order trace showing requests coming out in
 * priority order, not insertion order.
 */
public class MyHeap<T extends Comparable<T>> {

    private Object[] data;
    private int size;

    public MyHeap() {
        data = new Object[16];
        size = 0;
    }

    public void insert(T value) {
        // TODO place at data[size], then "bubble up" while it's smaller (or
        // larger, for a max-heap) than its parent at (i-1)/2. Resize if full.
        throw new UnsupportedOperationException("TODO");
    }

    @SuppressWarnings("unchecked")
    public T extractMin() {
        // TODO save data[0], move last element to root, shrink size,
        // then "sift down" swapping with the smaller child until heap order
        // is restored. Throw IllegalStateException if empty.
        throw new UnsupportedOperationException("TODO");
    }

    private void heapifyDown(int index) {
        // TODO
    }

    private void heapifyUp(int index) {
        // TODO
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
