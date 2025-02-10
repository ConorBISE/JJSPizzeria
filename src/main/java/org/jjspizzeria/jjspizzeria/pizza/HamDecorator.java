package org.jjspizzeria.jjspizzeria.pizza;

public class HamDecorator extends ToppingDecorator {
    public HamDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.50;
    }
}
