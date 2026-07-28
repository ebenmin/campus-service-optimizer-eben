package com.campus.optimizer.datastructures;

import java.util.Iterator;

/**
 * Custom singly or doubly linked list.
 * Required ops: addFirst, addLast, insertAfter, remove, iterator().
 * Required evidence: a node diagram (draw it in your report) plus a demo of
 * the iterator walking the list end to end.
 */
public class MyLinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
        // Node<T> prev; // uncomment if you implement doubly-linked
        Node(T value) { this.value = value; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addFirst(T value) {
        // TODO create node, point it at old head, update head (and tail if list was empty)
        throw new UnsupportedOperationException("TODO");
    }

    public void addLast(T value) {
        // TODO create node, attach after current tail, update tail (and head if list was empty)
        throw new UnsupportedOperationException("TODO");
    }

    public void insertAfter(T target, T value) {
        // TODO walk the list to find target, splice in a new node after it
        // throw NoSuchElementException if target isn't found (invalid-input test case)
        throw new UnsupportedOperationException("TODO");
    }

    public boolean remove(T value) {
        // TODO find and unlink the first node holding `value`; handle head/tail specially
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO return an Iterator<T> that walks from head to tail
        throw new UnsupportedOperationException("TODO");
    }
}
