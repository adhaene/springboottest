package com.bezkoder.spring.jpa.h2.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// BankAccountConcurrency.java
public class BankAccountConcurrency2 {


    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0); // Initial balance
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try  {
            List<Callable<String>> objects = new ArrayList<>();
            objects.add(new DepositTask(account, 50000));
            objects.add(new WithdrawTask(account, 5500));
            objects.add(new DepositTask(account, 500));
            objects.add(new WithdrawTask(account, 57700));
            objects.add(new DepositTask(account, 50330));
            objects.add(new WithdrawTask(account, 545600));
            objects.add(new DepositTask(account, 503450));
            objects.add(new WithdrawTask(account, 7555));
            List<Future<String>> futures = executorService.invokeAll(objects);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException| ExecutionException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            executorService.shutdown();
            System.err.println("Error submitting tasks: " + e.getMessage());
        }

//        try {
//            if (!executorService.awaitTermination(20, TimeUnit.SECONDS)) {
//                executorService.shutdownNow();
//            }
//        } catch (InterruptedException ex) {
//            executorService.shutdownNow();
//            Thread.currentThread().interrupt();
//        }

//        // Create threads for concurrent deposits and withdrawals
//        Thread t1 = new Thread(new DepositTask(account, 500), "Thread-Deposit-1");
//        Thread t2 = new Thread(new WithdrawTask(account, 200), "Thread-Withdraw-1");
//        Thread t3 = new Thread(new DepositTask(account, 300), "Thread-Deposit-2");
//        Thread t4 = new Thread(new WithdrawTask(account, 1500), "Thread-Withdraw-2"); // Will fail due to insufficient funds

//        // Start threads
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();

        // Wait for all threads to finish
//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//        } catch (InterruptedException e) {
//            System.out.println("Thread interrupted: " + e.getMessage());
//        }

        // Final balance
        System.out.println("Final Balance: " + account.getBalance());
    }
}

// BankAccount class with thread-safe methods
class BankAccount {
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
class DepositTask implements Callable {
    private final BankAccount account;
    private final double amount;

    public DepositTask(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

//    @Override
//    public void run() {
//        account.deposit(amount);
//    }

    @Override
    public String call() throws Exception {
                account.deposit(amount);

        return "deposit done of " + amount;
    }
}

// Runnable task for withdrawals
class WithdrawTask implements Callable {
    private final BankAccount account;
    private final double amount;

    public WithdrawTask(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

//    @Override
//    public void run() {
//        account.withdraw(amount);
//    }

    @Override
    public Object call() throws Exception {
        account.withdraw(amount);
        return "withdraw done of " + amount;    }
}
