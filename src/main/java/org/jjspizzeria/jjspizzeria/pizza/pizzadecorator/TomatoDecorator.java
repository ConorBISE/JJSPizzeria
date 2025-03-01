package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class TomatoDecorator extends ToppingDecorator {
    /**
     Decorator class for pizza
     Adds tomato to component
     */

    private final Topping tomato;

    //Constructor
    public TomatoDecorator(Pizza pizza) {
        super(pizza);
        tomato = new Topping("Tomato", 1.50, "tomato-topping.png");
    }

    public TomatoDecorator() {
        tomato = new Topping("Tomato", 1.0, "tomato-topping.png");
    }

    //Getters
    @Override
    public String getDescription() {
        return pizza.getDescription() + " + " + tomato.getName();
    }

    @Override
    public double getCost() {
        return pizza.getCost() + tomato.getCost();
    }

    @Override
    public String getPath() {
        return tomato.getPath();
    }

    public Topping getTopping(){
        return tomato;
    }
}
