package org.jjspizzeria.jjspizzeria.command;
import org.jjspizzeria.jjspizzeria.pizza.*;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.CheeseDecorator;

public class AddCheeseCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddCheeseCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        // Create the cheese decorator
        CheeseDecorator cheeseDecorator = new CheeseDecorator();
        pizzaManager.addTopping(cheeseDecorator);
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