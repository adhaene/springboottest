package com.bezkoder.spring.jpa.h2.thread;

public class NotifyExample {

    private static final Object lock = new Object();
    private static boolean ready = false;

    // Thread that waits for a signal
    static class WaitingThread extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(getName() + " is waiting...");
                while (!ready) { // Prevent spurious wakeups : where a thread can wake up from waiting without ever having received a notification.
                    try {
                        lock.wait(); // Releases lock and waits
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(getName() + " interrupted.");
                        return;
                    }
                }
                System.out.println(getName() + " received notification and proceeds.");
            }
        }
    }

    // Thread that sends the signal
    static class NotifyingThread extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                ready = true;
                System.out.println(getName() + " is notifying one waiting thread...");
                lock.notify(); // Wakes up one waiting thread
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new WaitingThread();
        Thread t2 = new WaitingThread();

        t1.start();
        t2.start();

        Thread.sleep(10000); // Ensure both threads are waiting

        Thread notifier = new NotifyingThread();
        notifier.start();

        t1.join();
        t2.join();
        notifier.join();
    }
}

