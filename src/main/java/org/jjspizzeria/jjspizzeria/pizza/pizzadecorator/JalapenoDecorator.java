package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class JalapenoDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds jalapeno to component
     */

    private final Topping jalapeno;

    //Constructor
    public JalapenoDecorator(Pizza pizza) {
        super(pizza);
        jalapeno = new Topping("Jalapeno", 1, "jalapeno-topping.png");
    }

    public JalapenoDecorator() {
        jalapeno = new Topping("Jalapeno", 1, "jalapeno-topping.png");
    }


    //Getters
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + jalapeno.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + jalapeno.getCost();
    }

    @Override
    public String getPath() {
        return jalapeno.getPath();
    }

    @Override
    public Topping getTopping(){
        return jalapeno;
    }
}