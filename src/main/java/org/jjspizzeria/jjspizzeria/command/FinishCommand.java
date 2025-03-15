package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;

public class FinishCommand extends Command {
    private final PizzaManager pizzaManager;

    public FinishCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        pizzaManager.finishPizza();
    }

    @Override
    public void undo() {
    }

}
