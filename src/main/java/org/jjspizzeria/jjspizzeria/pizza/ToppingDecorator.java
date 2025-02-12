package org.jjspizzeria.jjspizzeria.pizza;

public abstract class ToppingDecorator implements Pizza {
    //Super class for concrete topping decorators
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;

    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}
