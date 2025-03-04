package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

abstract public class OperationDecorator {
    protected Pizza pizza;
    public OperationDecorator(Pizza pizza){
        this.pizza = pizza;
    }
    public OperationDecorator(){
    }

    public String getDescription() {
        return pizza.getDescription();
    }
    public double getCost(){
        return pizza.getCost();
    }
}
