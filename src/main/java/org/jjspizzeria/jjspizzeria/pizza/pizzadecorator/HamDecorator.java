package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class HamDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds ham to component
     */

    private final Topping ham;

    //Constructor
    public HamDecorator(Pizza pizza) {
        super(pizza);
        ham = new Topping("Ham", 2, "ham-topping.png");
    }

    public HamDecorator() {
        ham = new Topping("Ham", 2, "ham-topping.png");
    }


    //Getters
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + ham.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + ham.getCost();
    }

    @Override
    public String getPath() {
        return ham.getPath();
    }

    @Override
    public Topping getTopping(){
        return ham;
    }
}