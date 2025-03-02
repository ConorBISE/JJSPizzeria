package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class BakeDecorator extends OperationDecorator{
    String bakeType;
    public BakeDecorator(Pizza pizza, String bakeType) {
        super(pizza);
        this.bakeType = bakeType;
    }

    public String getDescription() {
        return pizza.getDescription() + " " + bakeType + " baked";
    }

    public double getCost(){
        return pizza.getCost();
    }
}
