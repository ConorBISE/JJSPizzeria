package org.jjspizzeria.jjspizzeria.pizza.observer;

public interface Subject {
    /**
     * Registers an observer to receive updates.
     */
    void addObserver(PizzaObserver observer);

    /**
     * Unregisters an observer from receiving updates.
     */
    void removeObserver(PizzaObserver observer);

    /**
     * Notifies all registered observers with the current state.
     */
    void notifyObservers();
}