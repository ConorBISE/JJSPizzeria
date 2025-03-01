package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;

public class BakeCommand extends Command {
    private final String bakeStyle;
    private final PizzaManager pizzaManager;

    public BakeCommand(String bakeStyle) {
        this.pizzaManager = PizzaManager.getInstance();
        this.bakeStyle = bakeStyle;
    }


    @Override
    public void execute() {
        pizzaManager.bakePizza(bakeStyle);
    }

    @Override
    public void undo() {

    }
}
