package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public abstract class ToppingDecorator implements Pizza {
    //Super class for concrete topping decorators
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public ToppingDecorator() {

    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }

    public abstract Topping getTopping();
}
