package com.bezkoder.spring.jpa.h2.annotations;

import java.lang.reflect.Method;

// 3️⃣ Read and process annotations at runtime
public class AnnotationDemo {
    public static void main(String[] args) {
        try {
            Class<?> clazz = MyService.class;

            // Loop through all methods in MyService
            for (Method method : clazz.getDeclaredMethods()) {
                // Check if method has MyAnnotation
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

                    // Print annotation details
                    System.out.println("Method: " + method.getName());
                    System.out.println(" Author: " + annotation.author());
                    System.out.println(" Date: " + annotation.date());
                    System.out.println(" Revision: " + annotation.revision());
                    System.out.println("---------------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading annotations: " + e.getMessage());
        }
    }
}