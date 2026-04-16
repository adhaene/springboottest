package com.bezkoder.spring.jpa.h2.collections;

import java.util.HashMap;
import java.util.Map;

/*
Package: java.util
Implements: Map<K, V> interface.
Stores: Key–Value pairs.
Key Rules:
Keys are unique (duplicate keys overwrite the old value).
Values can be duplicated.
Null Handling:
Allows one null key.
Allows multiple null values.
Order: Unordered — does not guarantee insertion order.
Performance: Very fast for get() and put() (average O(1) time complexity).
Not Thread-Safe: Use Collections.synchronizedMap() or ConcurrentHashMap for thread safety.
 */
public class HashMapExample {
    public static void main(String[] args) {
        // Create a HashMap with String keys and Integer values
        Map<String, Integer> scores = new HashMap<>();

        // Adding key-value pairs
        scores.put("Alice", 85);
        scores.put("Bob", 92);
        scores.put("Charlie", 78);
        scores.put("Bob", 95); // Overwrites previous value for "Bob"
        scores.put(null, 100); // One null key allowed
        scores.put("David", null); // Multiple null values allowed

        // Accessing values
        System.out.println("Bob's score: " + scores.get("Bob"));
        System.out.println("Null key score: " + scores.get(null));

        // Checking if a key exists
        if (scores.containsKey("Alice")) {
            System.out.println("Alice is in the map.");
        }

        // Iterating over entries
        System.out.println("\nAll entries in the HashMap:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        // Removing a key
        scores.remove("Charlie");

        // Size of the map
        System.out.println("\nTotal entries after removal: " + scores.size());
    }
}
