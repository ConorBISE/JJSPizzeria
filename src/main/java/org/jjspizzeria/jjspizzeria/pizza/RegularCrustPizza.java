package org.jjspizzeria.jjspizzeria.pizza;

public class RegularCrustPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Regular Crust Pizza";
    }

    @Override
    public double getCost() {
        return 5;
    }

    @Override
    public String getPath() {
        return "path/to/image";
    }
}
