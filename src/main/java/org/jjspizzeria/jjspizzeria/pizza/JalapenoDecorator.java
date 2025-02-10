package org.jjspizzeria.jjspizzeria.pizza;

public class JalapenoDecorator extends ToppingDecorator {
    public JalapenoDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Jalapeno";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 0.50;
    }
}
