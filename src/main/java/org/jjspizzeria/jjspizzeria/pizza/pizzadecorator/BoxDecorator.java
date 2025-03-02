package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class BoxDecorator extends OperationDecorator{
    public BoxDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + " boxed";
    }

    public double getCost(){
        return pizza.getCost();
    }
}
