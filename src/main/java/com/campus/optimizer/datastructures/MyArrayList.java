package com.campus.optimizer.datastructures;

/**
 * Custom dynamic array. Required ops: insert, get, set, remove, resize.
 * Required evidence: unit tests (normal/boundary/invalid) + a trace showing
 * a resize event (e.g. print capacity before/after when the array grows).
 * Follow the pattern in MyStack.java (resizable Object[] + size counter).
 */
public class MyArrayList<T> {
    // TODO: private Object[] data; private int size;

    public MyArrayList() {
        // TODO initialise with a small starting capacity, e.g. 8
    }

    public void insert(int index, T value) {
        // TODO bounds-check index (0..size inclusive), resize if full, shift right
        throw new UnsupportedOperationException("TODO");
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        // TODO bounds-check index (0..size-1), throw IndexOutOfBoundsException if invalid
        throw new UnsupportedOperationException("TODO");
    }

    public void set(int index, T value) {
        // TODO bounds-check, overwrite
        throw new UnsupportedOperationException("TODO");
    }

    public T remove(int index) {
        // TODO bounds-check, shift left, shrink array if usage is low (optional)
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    private void resize(int newCapacity) {
        // TODO copy into a bigger backing array (see MyStack.resize for the pattern)
    }
}
