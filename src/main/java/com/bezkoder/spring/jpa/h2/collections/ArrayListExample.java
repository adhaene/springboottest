package com.bezkoder.spring.jpa.h2.collections;

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();

        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Banana"); // duplicates allowed

        // Insert at specific index
        fruits.add(1, "Mango");

        // Display all elements
        System.out.println("Index of Apple: " + fruits.indexOf("Apple"));

        // Display all elements
        System.out.println("Fruits List: " + fruits);

        // Access element
        System.out.println("Element at index 2: " + fruits.get(2));

        // Modify element
        fruits.set(2, "Blueberry");
        System.out.println("After modification: " + fruits);

        // Remove by index
        fruits.remove(3);
        System.out.println("After removing index 3: " + fruits);

        // Remove by value
        fruits.remove("Apple");
        System.out.println("After removing 'Apple': " + fruits);

        // Check if contains
        System.out.println("Contains 'Banana'? " + fruits.contains("Banana"));

        // Size of list
        System.out.println("List size: " + fruits.size());

        // Clear list
        fruits.clear();
        System.out.println("Is list empty? " + fruits.isEmpty());
    }
}

