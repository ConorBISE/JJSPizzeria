package org.jjspizzeria.jjspizzeria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jjspizzeria.jjspizzeria.pizza.PizzaRater;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

public class PizzaRaterTest {
    @Test
    public void testIdenticalToppingPizza() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(0.0, PizzaRater.pizzaScore(a, b));
    }

    @Test
    public void testMissedTopping() {
        Pizza a = new PineappleDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(1. / 3., PizzaRater.pizzaScore(a, b));
    }

    @Test
    public void testExtraTopping() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new PineappleDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())));

        assertEquals(1. / 2., PizzaRater.pizzaScore(a, b));
    }

    @Test
    public void testCorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal");

        assertEquals(0.0, PizzaRater.pizzaScore(a, b));
    }

    @Test
    public void testIncorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "crispy");

        assertEquals(1. / 3., PizzaRater.pizzaScore(a, b));
    }

    @Test
    public void testCorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal"), 4);

        assertEquals(0.0, PizzaRater.pizzaScore(a, b));
    }

    @Test
    public void testIncorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal"), 6);

        assertEquals(1. / 4., PizzaRater.pizzaScore(a, b));
    }
}
