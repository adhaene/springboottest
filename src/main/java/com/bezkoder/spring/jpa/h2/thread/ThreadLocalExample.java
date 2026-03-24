package com.bezkoder.spring.jpa.h2.thread;

/*
What is ThreadLocal?
ThreadLocal<T> provides thread-local variables — each thread gets its own isolated copy of the variable.
Useful for storing per-thread state without using synchronization.
Commonly used for:
User/session context in multi-threaded apps
Date formatters (SimpleDateFormat) to avoid thread-safety issues
Transaction or request IDs for logging

Key Points
Isolation – Values are not shared between threads.
Lifecycle – Value exists as long as the thread is alive (or until removed).
Memory Leaks – Must call .remove() when done, especially in thread pools.
Initialization – Can use ThreadLocal.withInitial(Supplier) for default values.

Each thread maintains its own counter independently.

✅ Best Practices:

Always call .remove() after use in long-lived threads (e.g., thread pools).
Avoid storing large objects in ThreadLocal.
Use InheritableThreadLocal if child threads need to inherit values.

 */
public class ThreadLocalExample {
    // ThreadLocal with initial value
    private static final ThreadLocal<Integer> threadLocalCounter =
            ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 1; i <= 3; i++) {
                threadLocalCounter.set(threadLocalCounter.get() + 1);
                System.out.printf("Thread %s: Counter = %d%n",
                        Thread.currentThread().getName(),
                        threadLocalCounter.get());
            }
            // Prevent memory leaks in thread pools
            threadLocalCounter.remove();
        };

        Thread t1 = new Thread(task, "A");
        Thread t2 = new Thread(task, "B");

        t1.start();
        t2.start();
    }
}
