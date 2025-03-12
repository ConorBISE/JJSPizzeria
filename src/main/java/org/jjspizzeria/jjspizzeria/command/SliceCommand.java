package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.SliceDecorator;

public class SliceCommand extends Command {
    private final PizzaManager pizzaManager;
    private final int slices;

    public SliceCommand(int slices) {
        pizzaManager = PizzaManager.getInstance();
        this.slices = slices;
    }

    @Override
    public void execute() {
        SliceDecorator sliceDecorator = new SliceDecorator(slices);
        pizzaManager.slicePizza(sliceDecorator);
    }

    @Override
    public void undo() {

    }
}
