package com.campus.optimizer.datastructures;

/**
 * Custom queue AND circular queue (fixed-capacity ring buffer).
 * Required ops: enqueue, dequeue, wrap-around handling.
 * Required evidence: a trace table showing front/rear indices moving,
 * including the point where rear wraps back to index 0.
 */
public class MyQueue<T> {

    private Object[] data;
    private int front;
    private int rear;
    private int size;

    public MyQueue(int capacity) {
        data = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T value) {
        // TODO if full, either resize (dynamic queue) or throw IllegalStateException
        //      (fixed-capacity circular queue) — pick one and document the choice.
        // rear = (rear + 1) % data.length; data[rear] = value; size++;
        throw new UnsupportedOperationException("TODO");
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        // TODO if empty, throw IllegalStateException (invalid-input test case)
        // T value = (T) data[front]; front = (front + 1) % data.length; size--;
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }
}
