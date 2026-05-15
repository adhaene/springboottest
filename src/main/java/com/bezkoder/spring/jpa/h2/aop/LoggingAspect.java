package com.bezkoder.spring.jpa.h2.aop;

// pom.xml dependency (Spring Boot example)
/*
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
*/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Aspect for logging execution time
@Aspect
@Component
public class LoggingAspect {

    // Pointcut: all methods in com.example.service package
    @Around("execution(* com.bezkoder.spring.jpa.h2.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed(); // Execute target method
        } finally {
            long duration = System.currentTimeMillis() - start;
            System.out.println(joinPoint.getSignature() + " executed in " + duration + "ms");
        }
    }
}
