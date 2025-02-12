package org.jjspizzeria.jjspizzeria.pizza;

public class BasePizza implements Pizza{

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
