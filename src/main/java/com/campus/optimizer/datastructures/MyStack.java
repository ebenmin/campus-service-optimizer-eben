package com.campus.optimizer.datastructures;

/**
 * Custom stack backed by a resizable array. WORKED EXAMPLE — read this file
 * first before writing your own structure; the other files in this package
 * follow the same shape (fields, resize/growth strategy, guard clauses for
 * invalid input, and a toString/trace helper for the required evidence).
 *
 * Required evidence: an undo-log or recursion-simulation trace showing push/pop
 * order (see MyStackTest.java for an example you can extend).
 */
public class MyStack<T> {

    private Object[] data;
    private int size;

    public MyStack() {
        data = new Object[8];
        size = 0;
    }

    public void push(T value) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("pop() called on an empty stack");
        }
        T value = (T) data[size - 1];
        data[--size] = null; // avoid holding a stale reference
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("peek() called on an empty stack");
        }
        return (T) data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        Object[] bigger = new Object[newCapacity];
        System.arraycopy(data, 0, bigger, 0, size);
        data = bigger;
    }
}
