package com.campus.optimizer.datastructures;

/**
 * Custom set and map built ON TOP OF your own MyHashTable or MyBST — do not
 * use java.util.HashSet/HashMap. This wraps one of your other structures to
 * demonstrate reuse (e.g. a set is a hash table where you ignore the value).
 * Required evidence: a membership-check use case and a lookup use case
 * (e.g. "is this location already visited during BFS?").
 */
public class MySetMap<T> {

    private final MyHashTable<T, Boolean> backing;

    public MySetMap(int capacity) {
        backing = new MyHashTable<>(capacity);
    }

    public void add(T value) {
        backing.put(value, Boolean.TRUE);
    }

    public boolean contains(T value) {
        return Boolean.TRUE.equals(backing.get(value));
    }

    public void remove(T value) {
        backing.remove(value);
    }
}
