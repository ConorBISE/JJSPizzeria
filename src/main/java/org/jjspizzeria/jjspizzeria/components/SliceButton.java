package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.pizza.PizzaState;

public class SliceButton extends ObserverButton {
    public SliceButton() {
        super();
        getStyleClass().add("slice-button");
    }


    @Override
    protected boolean shouldEnableForState(PizzaState state) {
        return state == PizzaState.BAKED;
    }
}
