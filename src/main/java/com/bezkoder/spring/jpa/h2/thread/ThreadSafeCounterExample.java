package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounterExample {

    // Thread-safe counter using AtomicInteger
    static class Counter {
        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet(); // Atomically increments by 1
        }

        public int getValue() {
            return count.get(); // Atomically reads the value
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        // Create two threads that increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                counter.increment();
                try {
                    Thread.sleep(1); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread 1 interrupted");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                counter.increment();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread 2 interrupted");
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted");
        }

        // Display the final counter value
        System.out.println("Final Counter Value: " + counter.getValue());
    }
}
