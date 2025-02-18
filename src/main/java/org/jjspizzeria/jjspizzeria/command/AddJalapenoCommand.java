package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;

public class AddJalapenoCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddJalapenoCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        // Create the cheese decorator
        JalapenoDecorator jalapenoDecorator = new JalapenoDecorator();
        pizzaManager.addTopping(jalapenoDecorator);
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