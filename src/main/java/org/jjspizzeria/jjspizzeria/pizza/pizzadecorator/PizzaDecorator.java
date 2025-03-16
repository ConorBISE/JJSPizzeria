package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    protected PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    protected PizzaDecorator() {
        
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }

    @Override
    public String getPath() {
        return pizza.getPath();
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
