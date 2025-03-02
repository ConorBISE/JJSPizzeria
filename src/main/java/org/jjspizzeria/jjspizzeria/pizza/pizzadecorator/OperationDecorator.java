package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

abstract public class OperationDecorator {
    protected Pizza pizza;

    public String getDescription() {
        return null;
    }
    public double getCost(){
        return 0;
    }
    public OperationDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}
