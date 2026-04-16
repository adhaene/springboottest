package com.bezkoder.spring.jpa.h2.collections;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Key Methods:

Add/Remove at head: addFirst(), removeFirst(), offerFirst(), pollFirst(), push(), pop()
Add/Remove at tail: addLast(), removeLast(), offerLast(), pollLast()
Peek: peekFirst(), peekLast()
 */
public class ArrayDequeExample {
    public static void main(String[] args) {
        // Create an ArrayDeque of Strings
        Deque<String> deque = new ArrayDeque<>();

        // Queue operations (FIFO)
        deque.offer("Alice");  // add to tail
        deque.offer("Bob");
        deque.offer("Charlie");

        System.out.println("Queue order:");
        while (!deque.isEmpty()) {
            System.out.println(deque.poll()); // remove from head
        }

        // Stack operations (LIFO)
        deque.push("Red");     // add to head
        deque.push("Green");
        deque.push("Blue");

        System.out.println("\nStack order:");
        while (!deque.isEmpty()) {
            System.out.println(deque.pop()); // remove from head
        }

        // Demonstrating add/remove from both ends
        deque.addFirst("First");
        deque.addLast("Last");
        System.out.println("\nDeque from both ends: " + deque);
    }
}
