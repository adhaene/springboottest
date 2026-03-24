package com.bezkoder.spring.jpa.h2.collections;


import java.util.LinkedList;

/*
Package: java.util
Implements: List, Deque, Queue interfaces.
Type: Doubly linked list — each node has references to previous and next nodes.
Key Features:
Allows duplicate elements.
Maintains insertion order.
Can store null elements.
Faster insertions/deletions in the middle compared to ArrayList.
Slower random access than ArrayList (no direct indexing in memory).
Common Methods:
add(E e), addFirst(E e), addLast(E e)
remove(), removeFirst(), removeLast()
get(int index), getFirst(), getLast()
size(), clear(), contains(Object o)
 */
public class LinkedListExample {
    public static void main(String[] args) {
        // Create a LinkedList of Strings
        LinkedList<String> fruits = new LinkedList<>();

        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Add at specific positions
        fruits.addFirst("Mango");
        fruits.addLast("Orange");

        // Display the LinkedList
        System.out.println("Fruits List: " + fruits);

        // Access elements
        System.out.println("First Fruit: " + fruits.getFirst());
        System.out.println("Last Fruit: " + fruits.getLast());
        System.out.println("Fruit at index 2: " + fruits.get(2));


        // Remove elements
        fruits.removeFirst();
        fruits.removeLast();
        fruits.remove("Banana");

        // insert at index
        fruits.add(12, "middle");

        // Check if list contains an element
        if (fruits.contains("Cherry")) {
            System.out.println("Cherry is in the list.");
        }

        // Display final list and size
        System.out.println("Updated Fruits List: " + fruits);
        System.out.println("List Size: " + fruits.size());
    }
}
