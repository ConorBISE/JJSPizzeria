package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class BasePizza implements Pizza {
    //Base class of pizza, used for decorator

    @Override
    public String getDescription() {
        return "Pizza";
    }

    @Override
    public double getCost() {
        return 9;
    }

    @Override
    public String getPath() {
        return "path/to/image";
    }
}
