package com.eltosevenz.collections.LinkedHashMap;

import java.util.*;

/*
capacity –
The initial capacity of the map (e.g., how many entries it can hold before resizing).
This does not limit the number of items – it just affects internal sizing/performance.

0.75f –
This is the load factor – a threshold that determines when to resize the internal structure.
A load factor of 0.75 means that when 75% of the map is full, it will resize (rehash).
It’s a trade-off between time (resizing cost) and space (memory usage).

true –
This is the accessOrder flag:

false = insertion order

true = access order – maintains order based on recent use, i.e., LRU behavior.
 */
public class LRUCache {
    Map<Integer, Integer> lru;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        lru = new java.util.LinkedHashMap<>(capacity,  0.75f, true); //Least Recently Used → Most Recently Used
    }
    public int get(int key) {
        return lru.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (lru.containsKey(key))
            lru.put(key,value); // Update moves key to most recent
        else{
            if (lru.size()>=capacity){
                Integer lrukey = lru.keySet().iterator().next(); // returns the first key in that order as recently accessed will move to last
                lru.remove(lrukey);
            }
            lru.put(key,value);
        }
    }

    public static void main(String[] arg){
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);        // cache: {1=1}
        cache.put(2, 2);        // cache: {1=1, 2=2}
        System.out.println(cache.get(1)); // returns 1, cache: {2=2, 1=1}
        cache.put(3, 3);        // evicts key 2, cache: {1=1, 3=3}
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(4, 4);        // evicts key 1, cache: {3=3, 4=4}
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
}