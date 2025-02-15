package org.jjspizzeria.jjspizzeria.pizza;

public class OnionDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds onion to component
     */
    private final Topping onion;

    //Constructor
    public OnionDecorator(Pizza pizza) {
        super(pizza);
        onion = new Topping ("Onion", 1, "onion.png");
    }

    public OnionDecorator() {
        onion = new Topping ("Onion", 1, "onion.png");
    }

    //Getters
    @Override
    public String getDescription()
    {
        return pizza.getDescription() + " + " + onion.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + onion.getCost();
    }

    @Override
    public String getPath() {
        return onion.getPath();
    }

    @Override
    public Topping getTopping(){
        return onion;
    }
}
