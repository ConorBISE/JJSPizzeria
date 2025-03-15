package org.jjspizzeria.jjspizzeria.command;

import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.BoxDecorator;

public class BoxCommand extends Command {
    private final PizzaManager pizzaManager;

    public BoxCommand() {
        this.pizzaManager = PizzaManager.getInstance();
    }

    @Override
    public void execute() {
        BoxDecorator boxDecorator = new BoxDecorator();
        pizzaManager.boxPizza(boxDecorator);
    }

}
