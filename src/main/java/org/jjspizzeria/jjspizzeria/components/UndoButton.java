package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.pizza.PizzaState;

public class UndoButton extends ObserverButton {
    public UndoButton() {
        super();
        setText("Undo");
        getStyleClass().add("topping-button");
    }

    @Override
    protected boolean shouldEnableForState(PizzaState state) {
        return state == PizzaState.UNBAKED;
    }

}
