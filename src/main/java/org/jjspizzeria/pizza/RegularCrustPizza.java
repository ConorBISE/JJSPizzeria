package org.jjspizzeria.pizza;

public class RegularCrustPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Regular Crust Pizza";
    }

    @Override
    public double getCost() {
        return 5;
    }
}
