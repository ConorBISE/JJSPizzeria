package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public interface  Pizza{
    //Interface for pizza, classes implement from this to create decorator pattern
    String getDescription();
    double getCost();
    String getPath();
}
