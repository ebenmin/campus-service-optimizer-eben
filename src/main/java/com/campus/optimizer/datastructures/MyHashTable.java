package com.campus.optimizer.datastructures;

/**
 * Custom hash table with chaining (a linked list per bucket) or open
 * addressing — pick one and document it.
 * Required ops: put, get, remove, collision handling.
 * Required evidence: collision counts measured at a few different load
 * factors (e.g. table size 16 with 8, 16, 32 keys) — this feeds directly
 * into the Week 4 performance experiment for hash tables.
 */
public class MyHashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next; // chaining
        Entry(K key, V value) { this.key = key; this.value = value; }
    }

    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        buckets = new Entry[capacity];
    }

    private int bucketIndex(K key) {
        // TODO use key.hashCode(), then floor-mod into [0, buckets.length)
        // int h = key.hashCode();
        // return Math.floorMod(h, buckets.length);
        throw new UnsupportedOperationException("TODO");
    }

    public void put(K key, V value) {
        // TODO find bucket, walk its chain: if key exists, overwrite value;
        // otherwise prepend/append a new Entry. Count a "collision" whenever
        // a bucket already has an entry before you insert.
        throw new UnsupportedOperationException("TODO");
    }

    public V get(K key) {
        // TODO find bucket, walk chain looking for a matching key; return
        // null (or throw, your choice — document it) if not found.
        throw new UnsupportedOperationException("TODO");
    }

    public V remove(K key) {
        // TODO find bucket, unlink the matching entry from the chain.
        throw new UnsupportedOperationException("TODO");
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }
}
