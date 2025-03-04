package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;

public class BoxCommand extends Command {
    private final PizzaManager pizzaManager;

    public BoxCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        pizzaManager.boxPizza();
    }

    @Override
    public void undo() {
    }

}
