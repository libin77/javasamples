package com.eltosevenz.collections.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

/*
An LRU (Least Recently Used) Cache is a data structure that evicts the least recently accessed item when the cache is full.
Java's LinkedHashMap provides an excellent foundation for implementing an LRU cache because it maintains the insertion order,
and with access-order mode enabled, it reorders entries on access.

Here’s a step-by-step implementation of an LRU Cache using LinkedHashMap:

1. Key Features of the Implementation
The cache has a fixed size (capacity).
It evicts the least recently used entry when the capacity is exceeded.
LinkedHashMap is used with access-order mode to reorder entries based on access.
 */
public class LRUCache<K, V> {
    private final int capacity; // Maximum size of the cache
    private final LinkedHashMap<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // Remove the eldest entry when size exceeds capacity
                return size() > LRUCache.this.capacity;
            }
        };
    }

    // Method to get a value from the cache
    public V get(K key) {
        return cache.getOrDefault(key, null);
    }

    // Method to add a key-value pair to the cache
    public void put(K key, V value) {
        cache.put(key, value);
    }

    // Method to display the current state of the cache
    public void display() {
        System.out.println(cache);
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        lruCache.display(); // Output: {1=One, 2=Two, 3=Three}

        lruCache.get(2);    // Access key 2
        lruCache.display(); // Output: {1=One, 3=Three, 2=Two}

        lruCache.put(4, "Four"); // Adds key 4, evicts key 1
        lruCache.display();      // Output: {3=Three, 2=Two, 4=Four}
    }
}

