package org.jjspizzeria.jjspizzeria.pizza;

public class CheeseCrustPizza implements Pizza{

    @Override
    public String getDescription() {
        return "Cheese Crust Pizza";
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
