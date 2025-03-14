package org.jjspizzeria.jjspizzeria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jjspizzeria.jjspizzeria.pizza.PizzaRater;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

public class PizzaRaterTest {
    @Test
    void testIdenticalToppingPizza() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(0.0, PizzaRater.pizzaScore(a, b));
    }

    @Test
    void testMissedTopping() {
        Pizza a = new PineappleDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(1. / 3., PizzaRater.pizzaScore(a, b));
    }

    @Test
    void testExtraTopping() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new PineappleDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())));

        assertEquals(1. / 2., PizzaRater.pizzaScore(a, b));
    }

    @Test
    void testCorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal");

        assertEquals(0.0, PizzaRater.pizzaScore(a, b));
    }

    @Test
    void testIncorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "crispy");

        assertEquals(1. / 3., PizzaRater.pizzaScore(a, b));
    }

    @Test
    void testCorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal"), 4);

        assertEquals(0.0, PizzaRater.pizzaScore(a, b));
    }

    @Test
    void testIncorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal"), 6);

        assertEquals(1. / 4., PizzaRater.pizzaScore(a, b));
    }
}
