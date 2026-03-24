package com.bezkoder.spring.jpa.h2.patterns.behavioral.strategy;

/*
Summary
Type: Behavioral Design Pattern (GoF)
Purpose: Allows selecting an algorithm’s behavior at runtime without changing the context class.
Key Idea: Define a family of algorithms, encapsulate each one, and make them interchangeable.
Benefits:
Promotes Open/Closed Principle (add new strategies without modifying existing code).
Avoids large conditional statements.
Makes code more flexible and testable.
Structure:
Strategy Interface – common method(s) for all algorithms.
Concrete Strategies – different implementations of the interface.
Context – uses a Strategy object to execute the algorithm.

How It Works
PaymentStrategy defines the common method pay().
CreditCardPayment and PayPalPayment implement different payment algorithms.
PaymentContext holds a reference to a PaymentStrategy and delegates the payment process to it.
The strategy can be swapped at runtime without changing the context logic.

 */
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy 1
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid £" + amount + " using Credit Card: " + cardNumber);
    }
}

// Concrete Strategy 2
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid £" + amount + " using PayPal account: " + email);
    }
}

// Context class
class PaymentContext {
    private PaymentStrategy strategy;

    // Allow changing strategy at runtime
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("Payment strategy not set.");
        }
        strategy.pay(amount);
    }
}

// Demo
public class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay with Credit Card
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.checkout(150.00);

        // Switch to PayPal
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.checkout(75.50);
    }
}
