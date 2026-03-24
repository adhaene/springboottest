package com.bezkoder.spring.jpa.h2.thread;

public class ThreadSafeCounterSynchronized {

    // Counter with synchronized methods
    static class Counter {
        private int count = 0;

        // Synchronized increment method
        public synchronized void increment() {
            count++;
        }

        // Synchronized getter
        public synchronized int getValue() {
            return count;
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        // Thread 1 increments the counter
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

        // Thread 2 increments the counter
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
