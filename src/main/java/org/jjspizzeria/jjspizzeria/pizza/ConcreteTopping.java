package org.jjspizzeria.jjspizzeria.pizza;

public class ConcreteTopping implements Pizza {
    private Pizza pizza;
    private ToppingEntity topping;

    public ConcreteTopping(Pizza pizza, ToppingEntity topping) {
        this.pizza = pizza;
        this.topping = topping;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + topping.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + topping.getCost();
    }

    @Override
    public String getPath() {
        return topping.getPath();
    }
}