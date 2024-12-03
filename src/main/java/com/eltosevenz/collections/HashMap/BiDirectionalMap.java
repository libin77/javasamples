package com.eltosevenz.collections.HashMap;

import java.util.HashMap;
import java.util.Map;

public class BiDirectionalMap<K extends Integer, V extends String> {
    private final Map<K, V> keyToValueMap = new HashMap<>();
    private final Map<V, K> valueToKeyMap = new HashMap<>();

    public void put(K key, V value) {
        keyToValueMap.put(key, value);
        valueToKeyMap.put(value, key);
    }

    public V get(K key) {
        return keyToValueMap.get(key);
    }

    public K get(V value) {
        return valueToKeyMap.get(value);
    }

    public void removeByKey(K key) {
        V value = keyToValueMap.remove(key);
        if (value != null) {
            valueToKeyMap.remove(value);
        }
    }

    public void removeByValue(V value) {
        K key = valueToKeyMap.remove(value);
        if (key != null) {
            keyToValueMap.remove(key);
        }
    }

    public static void main(String[] args) {
        BiDirectionalMap<Integer, String> map = new BiDirectionalMap<>();
        map.put(1, "One");
        map.put(2, "Two");

        System.out.println("Value for 'One': " + map.get("One")); // Output: 1
        System.out.println("Key for value 2: " + map.get(2));       // Output: Two
    }
}
