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

    @Test
    public void testCorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal");

        assertEquals(PizzaRater.pizzaScore(a, b), 0.0);
    }

    @Test
    public void testIncorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "crispy");

        assertEquals(PizzaRater.pizzaScore(a, b), 1. / 3.);
    }

    @Test
    public void testCorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal"), 4);

        assertEquals(PizzaRater.pizzaScore(a, b), 0.0);
    }

    @Test
    public void testIncorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "normal"), 6);

        assertEquals(PizzaRater.pizzaScore(a, b), 1. / 4.);
    }
}
