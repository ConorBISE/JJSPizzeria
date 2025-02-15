package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.MushroomDecorator;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;

public class AddMushroomCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddMushroomCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        // Create the cheese decorator
        MushroomDecorator mushroomDecorator = new MushroomDecorator();
        pizzaManager.addTopping(mushroomDecorator);
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