package org.jjspizzeria.jjspizzeria.command;
import org.jjspizzeria.jjspizzeria.pizza.*;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.HamDecorator;

public class AddHamCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddHamCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        // Create the cheese decorator
        HamDecorator hamDecorator = new HamDecorator();
        pizzaManager.addTopping(hamDecorator);
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