package org.jjspizzeria.pizza;

public class ThinCrustPizza implements Pizza{

    @Override
    public String getDescription() {
        return "Thin Crust Pizza";
    }

    @Override
    public double getCost() {
        return 7;
    }
}
