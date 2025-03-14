package org.jjspizzeria.jjspizzeria.components;

import javafx.scene.control.Button;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.PizzaState;
import org.jjspizzeria.jjspizzeria.pizza.observer.PizzaObserver;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;

public abstract class ObserverButton extends Button implements PizzaObserver {

    protected ObserverButton() {
        PizzaManager.getInstance().addObserver(this);
    }

    @Override
    public void onPizzaChanged(Pizza pizza, PizzaState state) {
        setDisable(!shouldEnableForState(state));
    }

    /**
     * Subclasses decide in which states this button should be enabled.
     */
    protected abstract boolean shouldEnableForState(PizzaState state);
}
