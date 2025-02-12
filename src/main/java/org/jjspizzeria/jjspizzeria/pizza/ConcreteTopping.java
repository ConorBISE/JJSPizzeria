package org.jjspizzeria.jjspizzeria.pizza;

public class ConcreteTopping extends ToppingDecorator {
    public ConcreteTopping (Pizza pizza, Topping topping) {
        super(pizza);
        this.topping = topping;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + topping.getDescription();
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