package com.bezkoder.spring.jpa.h2.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/*
Observer Pattern Summary
Type: Behavioral Design Pattern
Purpose: Defines a one-to-many dependency between objects so that when one object (the Subject) changes state, all its dependents (Observers) are automatically notified.
Use Cases:
Event handling systems
GUI frameworks
Messaging systems
Real-time data updates (e.g., stock prices, weather updates)
Key Components:

Subject (Observable)
Maintains a list of observers
Provides methods to attach/detach observers
Notifies observers of state changes
Observer
Defines an update() method to receive notifications
ConcreteSubject
Stores state and triggers notifications
ConcreteObserver
Implements the update() method to react to changes

How It Works
NewsAgency (Subject) keeps a list of NewsChannel (Observers).
When setNews() is called, it notifies all observers.
Each NewsChannel reacts independently to the update.
 */
// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    @Override
    public void attach(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void setNews(String news) {
        if (news != null && !news.equals(this.news)) {
            this.news = news;
            notifyObservers();
        }
    }
}

// Concrete Observer
class NewsChannel implements Observer {
    private String channelName;

    public NewsChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void update(String message) {
        System.out.println(channelName + " received news: " + message);
    }
}

// Demo
public class ObserverPatternDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        Observer channel1 = new NewsChannel("Channel One");
        Observer channel2 = new NewsChannel("Channel Two");

        agency.attach(channel1);
        agency.attach(channel2);

        agency.setNews("Breaking: Observer Pattern Implemented in Java!");
        agency.setNews("Update: Pattern working perfectly!");
    }
}
