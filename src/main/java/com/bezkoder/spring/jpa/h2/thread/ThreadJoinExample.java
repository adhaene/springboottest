package com.bezkoder.spring.jpa.h2.thread;

public class ThreadJoinExample {

    // A simple task for the thread
    static class MyTask extends Thread {
        private final String taskName;

        MyTask(String name) {
            this.taskName = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(taskName + " started.");
                // Simulate work with sleep
                for (int i = 1; i <= 3; i++) {
                    System.out.println(taskName + " working... step " + i);
                    Thread.sleep(1000); // 1 second pause
                }
                System.out.println(taskName + " finished.");
            } catch (InterruptedException e) {
                System.out.println(taskName + " was interrupted.");
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
    }

    public static void main(String[] args) {
        // Create two threads
        MyTask t1 = new MyTask("Thread-1");
        MyTask t2 = new MyTask("Thread-2");

        // Start both threads
        t1.start();
        t2.start();

        try {
            // Wait for t1 to finish before continuing
            System.out.println("Main thread waiting for Thread-1 to finish...");
            t1.join();

            // After t1 finishes, wait for t2
            System.out.println("Main thread waiting for Thread-2 to finish...");
            t2.join();

        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting.");
            Thread.currentThread().interrupt();
        }
        System.out.println("Main thread" + Thread.currentThread().getName());
        System.out.println("All threads have finished. Main thread exiting.");
    }
}

