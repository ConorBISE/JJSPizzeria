package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.TomatoDecorator;

public class AddTomatoCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddTomatoCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        TomatoDecorator tomatoDecorator = new TomatoDecorator();
        pizzaManager.addTopping(tomatoDecorator);
    }

    @Override
    public void undo() {
        pizzaManager.removeTopTopping();
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}