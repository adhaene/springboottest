package com.bezkoder.spring.jpa.h2.thread;

/*
A deadlock occurs when two or more threads are blocked forever,
 each waiting for a lock held by another thread.
 Necessary conditions:

Mutual Exclusion – Only one thread can hold a resource.
Hold and Wait – Thread holds one resource and waits for another.
No Preemption – Resources can’t be forcibly taken.
Circular Wait – Threads form a cycle, each waiting for the next.
 */
class Shared {
    synchronized void method1(Shared s) {
        System.out.println(Thread.currentThread().getName() + " in method1");
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        s.method2();
    }
    synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " in method2");
    }
}
public class DeadlockDemo {
    public static void main(String[] args) {
        Shared s1 = new Shared();
        Shared s2 = new Shared();
        new Thread(() -> s1.method1(s2), "T1").start();
        new Thread(() -> s2.method1(s1), "T2").start();
    }
}