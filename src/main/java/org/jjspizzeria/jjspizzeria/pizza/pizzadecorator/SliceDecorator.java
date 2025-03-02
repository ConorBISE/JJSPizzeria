package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class SliceDecorator extends OperationDecorator{
    int slices;

    public SliceDecorator(Pizza pizza, int slices) {
        super(pizza);
        this.slices = slices;
    }

    public String getDescription(){
        return pizza.getDescription() + " cut into " + slices + " slices";
    }

    public double getCost(){
        return pizza.getCost();
    }


}
