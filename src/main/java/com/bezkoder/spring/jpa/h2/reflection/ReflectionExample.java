package com.bezkoder.spring.jpa.h2.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greet() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }

    private void secretMethod() {
        System.out.println("This is a private method!");
    }
}

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            // 1. Load the class dynamically
            Class<?> personClass = Class.forName("Person");

            // 2. Get class name
            System.out.println("Class Name: " + personClass.getName());

            // 3. List all constructors
            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : personClass.getDeclaredConstructors()) {
                System.out.println(" " + constructor);
            }

            // 4. List all methods
            System.out.println("\nMethods:");
            for (Method method : personClass.getDeclaredMethods()) {
                System.out.println(" " + method.getName());
            }

            // 5. Create an instance using a constructor
            Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
            Object personObj = constructor.newInstance("Alice", 30);

            // 6. Invoke a public method
            Method greetMethod = personClass.getMethod("greet");
            greetMethod.invoke(personObj);

            // 7. Access and modify a private field
            Field nameField = personClass.getDeclaredField("name");
            nameField.setAccessible(true); // bypass private access
            nameField.set(personObj, "Bob");

            // Call greet again to see updated value
            greetMethod.invoke(personObj);

            // 8. Invoke a private method
            Method secretMethod = personClass.getDeclaredMethod("secretMethod");
            secretMethod.setAccessible(true);
            secretMethod.invoke(personObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
