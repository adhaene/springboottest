package com.bezkoder.spring.jpa.h2.bank;

// BankAccountConcurrency.java
public class BankAccountConcurrency {

    // BankAccount class with thread-safe methods
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }
            this.balance = initialBalance;
        }

        // Thread-safe deposit method
        public synchronized void deposit(double amount) {
            if (amount <= 0) {
                System.out.println(Thread.currentThread().getName() + " - Invalid deposit amount: " + amount);
                return;
            }
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount + " | New Balance: " + balance);
        }

        // Thread-safe withdraw method
        public synchronized void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println(Thread.currentThread().getName() + " - Invalid withdrawal amount: " + amount);
                return;
            }
            if (amount > balance) {
                System.out.println(Thread.currentThread().getName() + " - Insufficient funds for withdrawal: " + amount);
                return;
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + " | New Balance: " + balance);
        }

        public synchronized double getBalance() {
            return balance;
        }
    }

    // Runnable task for deposits
    static class DepositTask implements Runnable {
        private final BankAccount account;
        private final double amount;

        public DepositTask(BankAccount account, double amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            account.deposit(amount);
        }
    }

    // Runnable task for withdrawals
    static class WithdrawTask implements Runnable {
        private final BankAccount account;
        private final double amount;

        public WithdrawTask(BankAccount account, double amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            account.withdraw(amount);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0); // Initial balance

        // Create threads for concurrent deposits and withdrawals
        Thread t1 = new Thread(new DepositTask(account, 500), "Thread-Deposit-1");
        Thread t2 = new Thread(new WithdrawTask(account, 200), "Thread-Withdraw-1");
        Thread t3 = new Thread(new DepositTask(account, 300), "Thread-Deposit-2");
        Thread t4 = new Thread(new WithdrawTask(account, 1500), "Thread-Withdraw-2"); // Will fail due to insufficient funds

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        // Final balance
        System.out.println("Final Balance: " + account.getBalance());
    }
}
