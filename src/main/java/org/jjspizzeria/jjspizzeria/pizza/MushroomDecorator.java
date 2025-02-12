package org.jjspizzeria.jjspizzeria.pizza;

public class MushroomDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds mushroom to component
     */
    private final Topping mushroom;

    //Constructor
    public MushroomDecorator(Pizza pizza) {
        super(pizza);
        mushroom = new Topping ("Mushroom", 1, "mushroom.png");

    }

    //Getters
    @Override
    public String getDescription()
    {
        return pizza.getDescription() + " + " + mushroom.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + mushroom.getCost();
    }

    @Override
    public String getPath() {
        return mushroom.getPath();
    }

    public Topping getTopping(){
        return mushroom;
    }
}
