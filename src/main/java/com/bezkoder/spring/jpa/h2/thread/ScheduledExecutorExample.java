package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/*
Key Points:
Creating the Scheduler

Executors.newScheduledThreadPool(int nThreads) creates a pool for scheduled tasks.
Scheduling Methods

schedule(Runnable, delay, TimeUnit) → Runs once after a delay.
scheduleAtFixedRate(Runnable, initialDelay, period, TimeUnit) → Runs periodically at a fixed rate (start times are fixed).
scheduleWithFixedDelay(Runnable, initialDelay, delay, TimeUnit) → Runs periodically with a fixed delay between task completions.
        Shutdown Handling

Always call shutdown() or shutdownNow() to release resources.
        Use awaitTermination() to wait for tasks to finish.

 */

public class ScheduledExecutorExample {

    public static void main(String[] args) {
        // Create a ScheduledExecutorService with a thread pool of size 2
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        try {
            // Task 1: Run once after a 2-second delay
            scheduler.schedule(() -> {
                System.out.println("Task 1 executed after 2 seconds delay - " + Thread.currentThread().getName());
            }, 2, TimeUnit.SECONDS);

            // Task 2: Run repeatedly every 3 seconds, starting after 1 second
            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("Task 2 running every 3 seconds - " + Thread.currentThread().getName());
            }, 1, 3, TimeUnit.SECONDS);

            // Task 3: Run repeatedly with a fixed delay of 4 seconds between completions
            scheduler.scheduleWithFixedDelay(() -> {
                System.out.println("Task 3 running with 4 seconds delay after completion - " + Thread.currentThread().getName());
            }, 1, 4, TimeUnit.SECONDS);

            // Let the scheduler run for 15 seconds before shutting down
            Thread.sleep(15000);

        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            // Gracefully shut down the scheduler
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
}

