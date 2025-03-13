package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.pizza.PizzaState;

public class BoxButton extends ObserverButton {
    public BoxButton() {
        super();
        getStyleClass().add("end-button");
    }

    @Override
    protected boolean shouldEnableForState(PizzaState state) {
        return state == PizzaState.SLICED;
    }
}
