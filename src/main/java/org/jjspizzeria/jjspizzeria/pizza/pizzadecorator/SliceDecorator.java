package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class SliceDecorator extends OperationDecorator{
    private final int slices;

    public SliceDecorator(Pizza pizza, int slices) {
        super(pizza);
        this.slices = slices;
    }
    public SliceDecorator(int slices) {
        this.slices = slices;
    }

    public String getDescription(){
        return pizza.getDescription() + " cut into " + slices + " slices";
    }

    public int getSlices(){
        return slices;
    }


}
