package org.jjspizzeria.jjspizzeria.pizza;

public class PepperoniDecorator extends ToppingDecorator {
    Topping pepperoni = new Topping (", Pepperoni", 1, "pepperoni.png");
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);

    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + pepperoni.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + pepperoni.getCost();
    }

    @Override
    public String getPath() {
        return pepperoni.getPath();
    }
}