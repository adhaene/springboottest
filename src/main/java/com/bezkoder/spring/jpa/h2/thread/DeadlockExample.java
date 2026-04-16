package com.bezkoder.spring.jpa.h2.thread;

/*
In Java, a deadlock occurs when two or more threads are waiting indefinitely
 for each other to release resources, and none of them can proceed.

This usually happens when:

Multiple threads hold locks on different resources.
Each thread tries to acquire a lock on a resource already held by another thread.
A circular wait condition is created.
 */
public class DeadlockExample {

    // Two shared resources
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Thread 1 locks resource1 first, then tries to lock resource2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");

                try { Thread.sleep(100); } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Thread 1: Waiting for resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        // Thread 2 locks resource2 first, then tries to lock resource1
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked resource 2");

                try { Thread.sleep(100); } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Thread 2: Waiting for resource 1...");
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
