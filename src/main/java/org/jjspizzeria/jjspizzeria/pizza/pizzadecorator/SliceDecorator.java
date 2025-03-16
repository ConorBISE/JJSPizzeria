package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class SliceDecorator extends PizzaDecorator {
    private final int slices;

    public SliceDecorator(Pizza pizza, int slices) {
        super(pizza);
        this.slices = slices;
    }
    public SliceDecorator(int slices) {
        this.slices = slices;
    }

    @Override
    public String getDescription(){
        return pizza.getDescription() + " cut into " + slices + " slices";
    }

    public int getSlices(){
        return slices;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + slices;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
    
        if (obj == null)
            return false;
    
        if (getClass() != obj.getClass())
            return false;
    
        SliceDecorator other = (SliceDecorator) obj;
        if (slices != other.slices)
            return false;
    
        return true;
    }
    
}
