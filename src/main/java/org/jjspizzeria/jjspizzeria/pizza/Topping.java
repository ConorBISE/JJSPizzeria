package org.jjspizzeria.jjspizzeria.pizza;

public class Topping {
    /**
     Topping entity, contains information about each topping
     Name: name of topping
     Cost: cost of adding topping to pizza
     Path: path to the image of topping
     */


    private String name;
    private double cost;
    private String path;

    //Constructor
    public Topping(String name, double cost, String path) {
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
