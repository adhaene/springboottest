package com.bezkoder.spring.jpa.h2.collections;

import java.util.TreeSet;
import java.util.Comparator;

/*
Package: java.util
Implements: NavigableSet, SortedSet
Internal Structure: Red-Black Tree (self-balancing binary search tree)
Key Features:
Stores unique elements (no duplicates)
Maintains ascending sorted order by default
Allows custom sorting via Comparator
Null Handling: Only one null allowed if using natural ordering (but not for TreeSet of String or other Comparable types that disallow null)
Not synchronized (use Collections.synchronizedSortedSet() for thread safety)
Time Complexity:
Add, Remove, Search → O(log n)
 */
public class TreeSetExample {
    public static void main(String[] args) {
        // Create a TreeSet with natural ordering
        TreeSet<Integer> numbers = new TreeSet<>();

        // Adding elements (duplicates are ignored)
        numbers.add(50);
        numbers.add(10);
        numbers.add(30);
        numbers.add(20);
        numbers.add(10); // Duplicate, will be ignored

        System.out.println("TreeSet (Natural Order): " + numbers);

        // Accessing elements
        System.out.println("First Element: " + numbers.first());
        System.out.println("Last Element: " + numbers.last());

        // Removing an element
        numbers.remove(30);
        System.out.println("After Removing 30: " + numbers);

        // Creating a TreeSet with custom descending order
        TreeSet<Integer> descNumbers = new TreeSet<>(Comparator.reverseOrder());
        descNumbers.addAll(numbers);
        System.out.println("TreeSet (Descending Order): " + descNumbers);

        // NavigableSet methods
        System.out.println("Higher than 20: " + numbers.higher(20));
        System.out.println("Lower than 20: " + numbers.lower(20));
    }
}

