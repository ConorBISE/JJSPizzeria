package org.jjspizzeria.jjspizzeria.pizza.observer;

import org.jjspizzeria.jjspizzeria.pizza.PizzaState;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;

public interface PizzaObserver {
    /**
     * Called whenever the pizza state or topping changes.
     *
     * @param pizza The current pizza (decorator chain).
     * @param state The current state of the pizza.
     */
    void onPizzaChanged(Pizza pizza, PizzaState state);
}
