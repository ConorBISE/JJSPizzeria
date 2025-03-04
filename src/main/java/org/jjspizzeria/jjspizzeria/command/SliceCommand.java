package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;

public class SliceCommand extends Command {
    private final PizzaManager pizzaManager;
    private final int slices;

    public SliceCommand(int slices) {
        pizzaManager = PizzaManager.getInstance();
        this.slices = slices;
    }

    @Override
    public void execute() {
        pizzaManager.slicePizza(slices);
    }

    @Override
    public void undo() {

    }
}
