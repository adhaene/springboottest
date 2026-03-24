package com.bezkoder.spring.jpa.h2.thread;

import java.util.concurrent.*;

public class WorkStealingExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newWorkStealingPool();

        // Submit multiple tasks
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " run by " + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            });
        }

        // Allow time for tasks to complete
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }
}
