package org.jjspizzeria.jjspizzeria.pizza;

public class CheeseDecorator extends ToppingDecorator {
    Topping cheese = new Topping ("Cheese", 1.50, "cheese.png");
    public CheeseDecorator(Pizza pizza) {
        super(pizza);

    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + cheese.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + cheese.getCost();
    }

    @Override
    public String getPath() {
        return cheese.getPath();
    }
}