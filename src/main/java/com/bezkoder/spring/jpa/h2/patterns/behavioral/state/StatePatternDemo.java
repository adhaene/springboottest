package com.bezkoder.spring.jpa.h2.patterns.behavioral.state;

/*
Summary
Purpose: Allows an object to change its behavior when its internal state changes, making it appear as if the object has changed its class.
Type: Behavioral design pattern (GoF).
Key Idea: Encapsulate state-specific behavior into separate classes and delegate state-related work to the current state object.
Benefits:
Eliminates large conditional statements (if/else or switch).
Makes state transitions explicit.
Improves maintainability and scalability.
When to Use:
When an object’s behavior depends on its state.
When state transitions are frequent and complex.

How It Works
State Interface defines actions (insertCoin, selectProduct, dispense).
Concrete States (ReadyState, HasCoinState, DispensingState) implement behavior for each state.
Context (VendingMachine) holds a reference to the current state and delegates actions to it.
State Transitions are handled inside state classes by calling machine.setState(...).

 */
// State interface
interface State {
    void insertCoin();
    void selectProduct();
    void dispense();
}

// Concrete State: Ready
class ReadyState implements State {
    private final VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void selectProduct() {
        System.out.println("Insert coin first.");
    }

    @Override
    public void dispense() {
        System.out.println("Insert coin and select product first.");
    }
}

// Concrete State: Has Coin
class HasCoinState implements State {
    private final VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected.");
        machine.setState(machine.getDispensingState());
    }

    @Override
    public void dispense() {
        System.out.println("Select product first.");
    }
}

// Concrete State: Dispensing
class DispensingState implements State {
    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Already dispensing.");
    }

    @Override
    public void dispense() {
        System.out.println("Product dispensed.");
        machine.setState(machine.getReadyState());
    }
}

// Context
class VendingMachine {
    private final State readyState;
    private final State hasCoinState;
    private final State dispensingState;

    private State currentState;

    public VendingMachine() {
        readyState = new ReadyState(this);
        hasCoinState = new HasCoinState(this);
        dispensingState = new DispensingState(this);
        currentState = readyState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getReadyState() { return readyState; }
    public State getHasCoinState() { return hasCoinState; }
    public State getDispensingState() { return dispensingState; }

    // Actions
    public void insertCoin() { currentState.insertCoin(); }
    public void selectProduct() { currentState.selectProduct(); }
    public void dispense() { currentState.dispense(); }
}

// Demo
public class StatePatternDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.selectProduct(); // No coin yet
        machine.insertCoin();
        machine.insertCoin();    // Already has coin
        machine.selectProduct();
        machine.dispense();
    }
}
