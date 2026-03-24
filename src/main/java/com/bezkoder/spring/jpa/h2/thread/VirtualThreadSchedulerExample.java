package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/*
How This Works
Why not a pure virtual-thread scheduler?

ScheduledExecutorService still needs a platform thread for timing.
We wrap each scheduled task in Thread.startVirtualThread(...) so the actual work runs in a virtual thread.
Benefits

Virtual threads are extremely lightweight — you can run thousands without exhausting memory.
Each scheduled execution is isolated in its own virtual thread.
Java 21+ Requirement

Virtual threads are part of Project Loom, officially available in Java 21.
Compile and run with:
Bash

Copy code
javac VirtualThreadSchedulerExample.java
java VirtualThreadSchedulerExample
 */
public class VirtualThreadSchedulerExample {

    public static void main(String[] args) {
        // Create a platform-thread scheduler (needed for timing)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        try {
            // Task 1: Run once after 2 seconds in a virtual thread
            scheduler.schedule(() -> runInVirtualThread("Task 1 executed after 2 seconds delay"),
                    2, TimeUnit.SECONDS);

            // Task 2: Run every 3 seconds starting after 1 second in a virtual thread
            scheduler.scheduleAtFixedRate(() -> runInVirtualThread("Task 2 running every 3 seconds"),
                    1, 3, TimeUnit.SECONDS);

            // Task 3: Run with a fixed delay of 4 seconds between completions in a virtual thread
            scheduler.scheduleWithFixedDelay(() -> runInVirtualThread("Task 3 running with 4 seconds delay after completion"),
                    1, 4, TimeUnit.SECONDS);

            // Let the scheduler run for 15 seconds before shutting down
            Thread.sleep(15000);

        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            // Gracefully shut down
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
            System.out.println("Scheduler shut down.");
        }
    }

    /**
     * Runs the given message in a virtual thread.
     */
    private static void runInVirtualThread(String message) {
//        Thread.startVirtualThread(() -> {
//            System.out.println(message + " - " + Thread.currentThread());
//            try {
//                // Simulate some work
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        });
    }
}
