package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.pizza.PizzaState;

public class BakeButton extends ObserverButton {
    public BakeButton() {
        super();
        getStyleClass().add("bake-button");
    }

    @Override
    protected boolean shouldEnableForState(PizzaState state) {
        return state == PizzaState.UNBAKED;
    }
}
