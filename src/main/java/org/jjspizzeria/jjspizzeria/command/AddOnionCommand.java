package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.OnionDecorator;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;

public class AddOnionCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddOnionCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        // Create the cheese decorator
        OnionDecorator onionDecorator = new OnionDecorator();
        pizzaManager.addTopping(onionDecorator);
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