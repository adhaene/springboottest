package com.bezkoder.spring.jpa.h2.thread;

class SharedResource {
    synchronized void waitMethod() {
        try {
            System.out.println("Waiting...");
            this.wait(); // Releases lock and waits
            System.out.println("Resumed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    synchronized void notifyMethod() {
        System.out.println("Notifying one thread...");
        this.notify(); // Wakes one waiting thread
        //this.notifyAll(); // Wakes all waiting thread
    }

}

public class NotifyExample2 {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread t1 = new Thread(resource::waitMethod);
        Thread t3 = new Thread(resource::waitMethod);
        Thread t2 = new Thread(resource::notifyMethod);

        t1.start();
        t3.start();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        t2.start();
    }
}

