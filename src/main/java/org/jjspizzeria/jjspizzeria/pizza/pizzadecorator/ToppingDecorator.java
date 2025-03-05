package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public abstract class ToppingDecorator extends PizzaDecorator {
    //Super class for concrete topping decorators
    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public ToppingDecorator() {

    }

    public abstract Topping getTopping();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToppingDecorator))
            return false;

        ToppingDecorator other = (ToppingDecorator)obj;

        return getTopping().equals(other.getTopping());
    }
}
