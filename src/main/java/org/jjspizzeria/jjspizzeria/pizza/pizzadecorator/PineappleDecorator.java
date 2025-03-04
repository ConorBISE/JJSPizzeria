package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class PineappleDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds pineapple to component
     */

    private final Topping pineapple;

    //Constructor
    public PineappleDecorator(Pizza pizza) {
        super(pizza);
        pineapple = new Topping("Pineapple", 1.50, "pineapple-topping.png");
    }

    public PineappleDecorator() {
        pineapple = new Topping("Pineapple", 1.0, "pineapple-topping.png");
    }

    //Getters
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + pineapple.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + pineapple.getCost();
    }

    @Override
    public String getPath() {
        return pineapple.getPath();
    }

    public Topping getTopping(){
        return pineapple;
    }
}
