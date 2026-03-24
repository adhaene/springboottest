package com.bezkoder.spring.jpa.h2.thread;

public class VirtualThreadExample {
//    public static void main(String[] args) throws InterruptedException {
//        // Create and start a virtual thread
//        Thread vt = Thread.ofVirtual().start(() -> {
//            System.out.println("Hello from virtual thread: " + Thread.currentThread());
//        });
//
//        vt.join(); // Wait for the virtual thread to finish
//
//        // Using an Executor with virtual threads
//        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
//            for (int i = 0; i < 5; i++) {
//                int taskId = i;
//                executor.submit(() -> {
//                    System.out.println("Task " + taskId + " running on " + Thread.currentThread());
//                    Thread.sleep(500); // Simulate work
//                    return null;
//                });
//            }
//        } // Executor closes automatically
//    }
}
