package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
How It Works:
Executor Creation
Executors.newFixedThreadPool(3) creates a pool of 3 threads.
        Task Submission
We submit 5 tasks; the executor runs up to 3 at the same time.
Graceful Shutdown
shutdown() stops accepting new tasks.
awaitTermination() waits for tasks to finish.
If tasks don’t finish in time, shutdownNow() forces termination.
Thread Safety
Each task runs independently in its own thread from the pool.
     */

public class ThreadExecutorExample {

    // A simple task that implements Runnable
    static class MyTask implements Runnable {
        private final int taskId;

        public MyTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                // Simulate work
                Thread.sleep(1000);
                System.out.println("Task " + taskId + " completed.");
            } catch (InterruptedException e) {
                System.err.println("Task " + taskId + " was interrupted.");
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
    }

    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            // Submit 5 tasks to the executor
            for (int i = 1; i <= 5; i++) {
                executor.submit(new MyTask(i));
            }
        } catch (Exception e) {
            System.err.println("Error submitting tasks: " + e.getMessage());
        } finally {
            // Initiate shutdown
            executor.shutdown();
            try {
                // Wait for all tasks to finish or timeout after 10 seconds
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.out.println("Forcing shutdown...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.err.println("Shutdown interrupted.");
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All tasks finished or executor shut down.");
    }
}
