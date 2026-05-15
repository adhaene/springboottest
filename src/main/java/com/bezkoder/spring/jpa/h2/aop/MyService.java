package com.bezkoder.spring.jpa.h2.aop;

// Example service
//package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void doWork() throws InterruptedException {
        Thread.sleep(500); // Simulate work
        System.out.println("Work done!");
    }
}
