package com.bezkoder.spring.jpa.h2.thread;

/*
How to Avoid Deadlock

While it is challenging to completely eliminate the possibility of deadlocks,
 the following strategies can significantly reduce their occurrence:

Avoid Nested Locks: Minimize the use of nested synchronized blocks or methods.
 If a thread already holds a lock, avoid acquiring additional locks within the same thread.

Use Lock Ordering: Assign a consistent order to acquire locks.
For example, always acquire locks in the same sequence (e.g., lock Object-A before Object-B)
 to prevent circular dependencies.

Avoid Unnecessary Locks: Only lock critical sections of code or shared resources
that truly require synchronization. Avoid locking resources unnecessarily.

Use Thread.join() with Timeout: If one thread must wait for another to complete,
 use the Thread.join() method with a specified timeout to prevent indefinite waiting.

Lock Timeout: Use APIs like ReentrantLock from java.util.concurrent
that allow specifying a timeout for acquiring locks.
If the lock cannot be acquired within the timeout,
the thread can proceed with alternative logic.

Lock-Free Data Structures: Where possible, use concurrent collections like ConcurrentHashMap or ConcurrentLinkedQueue that are designed to handle multithreading without explicit locking.

 */
class Resource {
    private final int id;
    Resource(int id) {
        this.id = id;
    }
    synchronized void lock(Resource other) {
        System.out.println(Thread.currentThread().getName() + " locks Resource " + id);
        synchronized (other) {
            System.out.println(Thread.currentThread().getName() + " locks Resource " + other.id);
        }
    }
}
public class AvoidDeadlockExample {
    public static void main(String[] args) {
        Resource r1 = new Resource(1);
        Resource r2 = new Resource(2);
        Thread t1 = new Thread(() -> r1.lock(r2), "Thread1");
        Thread t2 = new Thread(() -> r2.lock(r1), "Thread2");
        t1.start();
        t2.start();
    }
}