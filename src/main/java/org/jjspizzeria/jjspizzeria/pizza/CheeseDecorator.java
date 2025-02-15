package org.jjspizzeria.jjspizzeria.pizza;

public class CheeseDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds cheese to component
     */

    private final Topping cheese;

    //Constructor
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
        cheese = new Topping ("Cheese", 1.50, "cheese.png");
    }

    public CheeseDecorator() {
        cheese = new Topping("Cheese", 1.50, "cheese.png");
    }

    //Getters
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + cheese.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + cheese.getCost();
    }

    @Override
    public String getPath() {
        return cheese.getPath();
    }

    public Topping getTopping(){
        return cheese;
    }
}