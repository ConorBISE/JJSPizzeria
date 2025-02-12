package org.jjspizzeria.jjspizzeria.pizza;

public class ThinCrustPizza implements Pizza{

    @Override
    public String getDescription() {
        return "Thin Crust Pizza";
    }

    @Override
    public double getCost() {
        return 7;
    }

    @Override
    public String getPath() {
        return "path/to/image";
    }
}
