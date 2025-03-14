package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public abstract class ToppingDecorator extends PizzaDecorator {
    //Super class for concrete topping decorators
    protected ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    protected ToppingDecorator() {

    }

    public abstract Topping getTopping();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToppingDecorator))
            return false;

        ToppingDecorator other = (ToppingDecorator)obj;

        return getTopping().equals(other.getTopping());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopping() == null) ? 0 : getTopping().hashCode());
        return result;
    }
}
