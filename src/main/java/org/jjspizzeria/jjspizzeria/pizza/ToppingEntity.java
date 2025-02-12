package org.jjspizzeria.jjspizzeria.pizza;

public class ToppingEntity {
    private String name;
    private double cost;
    private String path;

    public ToppingEntity(String name, double cost, String path) {
        this.name = name;
        this.cost = cost;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }

    public String getPath() {
        return this.path;
    }
}
