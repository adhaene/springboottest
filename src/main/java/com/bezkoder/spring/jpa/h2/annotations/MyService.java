package com.bezkoder.spring.jpa.h2.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;



// 2️⃣ Use the annotation in a class
class MyService {

    @MyAnnotation(author = "Alice", date = "2026-02-24", revision = 2)
    public void processData() {
        System.out.println("Processing data...");
    }

    @MyAnnotation(author = "Bob", date = "2026-02-20")
    public void generateReport() {
        System.out.println("Generating report...");
    }
}


