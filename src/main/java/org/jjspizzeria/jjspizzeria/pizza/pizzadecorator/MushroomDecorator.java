package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class MushroomDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds mushroom to component
     */
    private final Topping mushroom;

    //Constructor
    public MushroomDecorator(Pizza pizza) {
        super(pizza);
        mushroom = new Topping("Mushroom", 1, "mushroom-topping.png");
    }

    public MushroomDecorator() {
        mushroom = new Topping("Mushroom", 1, "mushroom-topping.png");
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

    @Override
    public Topping getTopping(){
        return mushroom;
    }
}
