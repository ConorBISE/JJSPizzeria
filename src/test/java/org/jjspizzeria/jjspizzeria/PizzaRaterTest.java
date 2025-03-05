package org.jjspizzeria.jjspizzeria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jjspizzeria.jjspizzeria.pizza.PizzaRater;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

public class PizzaRaterTest {
    @Test
    public void testIdenticalPizza() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(PizzaRater.pizzaScore(a, b), 0.0);
    }

    @Test
    public void testMissedTopping() {
        Pizza a = new PineappleDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(PizzaRater.pizzaScore(a, b), 1. / 3.);
    }

    @Test
    public void testExtraTopping() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new PineappleDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())));

        assertEquals(PizzaRater.pizzaScore(a, b), 1. / 2.);
    }
}
