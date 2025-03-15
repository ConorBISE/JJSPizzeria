package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class BoxDecorator extends PizzaDecorator {
    public BoxDecorator(Pizza pizza) {
        super(pizza);
    }

    public BoxDecorator() {
        this(null);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " boxed";
    }

}
