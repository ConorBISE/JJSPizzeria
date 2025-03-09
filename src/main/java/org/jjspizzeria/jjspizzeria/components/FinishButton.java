package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.pizza.PizzaState;

public class FinishButton extends ObserverButton {
    public FinishButton() {
        super();
        getStyleClass().add("end-button");
    }

    @Override
    protected boolean shouldEnableForState(PizzaState state) {
        return state == PizzaState.BOXED;
    }
}

