package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.PineappleDecorator;

public class AddPineappleCommand extends Command {
    private final PizzaManager pizzaManager;

    public AddPineappleCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        PineappleDecorator pineappleDecorator = new PineappleDecorator();
        pizzaManager.addTopping(pineappleDecorator);
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