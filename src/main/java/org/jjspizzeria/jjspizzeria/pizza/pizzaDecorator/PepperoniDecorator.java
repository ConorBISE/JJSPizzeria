package org.jjspizzeria.jjspizzeria.pizza.pizzaDecorator;

public class PepperoniDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds pepperoni to component
     */
    private final Topping pepperoni;

    //Constructor
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
        pepperoni = new Topping("Pepperoni", 2, "pepperoni.png");
    }

    public PepperoniDecorator() {
        pepperoni = new Topping("Pepperoni", 2, "pepperoni.png");
    }

    //Getters
    @Override
    public String getDescription()
    {
        return pizza.getDescription() + " + " + pepperoni.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + pepperoni.getCost();
    }

    @Override
    public String getPath() {
        return pepperoni.getPath();
    }

    @Override
    public Topping getTopping(){
        return pepperoni;
    }
}