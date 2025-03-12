package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.BakeDecorator;

public class BakeCommand extends Command {
    private final PizzaManager pizzaManager;
    private final String bakeStyle;

    public BakeCommand(String bakeStyle) {
        this.pizzaManager = PizzaManager.getInstance();
        this.bakeStyle = bakeStyle;
    }


    @Override
    public void execute() {
        BakeDecorator bakeDecorator = new BakeDecorator(bakeStyle);
        pizzaManager.bakePizza(bakeDecorator);
    }

    @Override
    public void undo() {

    }
}
