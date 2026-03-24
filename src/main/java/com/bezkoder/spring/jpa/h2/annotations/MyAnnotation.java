package com.bezkoder.spring.jpa.h2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 1️⃣ Define a custom annotation
@Retention(RetentionPolicy.RUNTIME) // Available at runtime
@Target(ElementType.METHOD)         // Can be applied to methods only
@interface MyAnnotation {
    String author();
    String date();
    int revision() default 1; // Optional element with default value
}