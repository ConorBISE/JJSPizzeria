package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzaDecorator.PepperoniDecorator;

public class AddPepperoniCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddPepperoniCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        // Create the cheese decorator
        PepperoniDecorator pepperoniDecorator = new PepperoniDecorator();
        pizzaManager.addTopping(pepperoniDecorator);
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