package org.jjspizzeria.jjspizzeria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jjspizzeria.jjspizzeria.pizza.PizzaRater;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

public class PizzaRaterTest {
    private static double DELTA = 1e-6;
    
    @Test
    void testIdenticalToppingPizza() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(1.0, PizzaRater.pizzaScore(a, b), DELTA);
    }

    @Test
    void testMissedTopping() {
        Pizza a = new PineappleDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())));
        Pizza b = new JalapenoDecorator(new HamDecorator(new BasePizza()));

        assertEquals(2. / 3., PizzaRater.pizzaScore(a, b), DELTA);
    }

    @Test
    void testExtraTopping() {
        Pizza a = new HamDecorator(new JalapenoDecorator(new BasePizza()));
        Pizza b = new PineappleDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())));

        assertEquals(1. / 2., PizzaRater.pizzaScore(a, b), DELTA);
    }

    @Test
    void testCorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "Normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "Normal");

        assertEquals(1.0, PizzaRater.pizzaScore(a, b), DELTA);
    }

    @Test
    void testIncorrectBakeDecorator() {
        Pizza a = new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "Normal");
        Pizza b = new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "Crispy");

        assertEquals(2. / 3., PizzaRater.pizzaScore(a, b), DELTA);
    }

    @Test
    void testCorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "Normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "Normal"), 4);

        assertEquals(1.0, PizzaRater.pizzaScore(a, b), DELTA);
    }

    @Test
    void testIncorrectSliceDecorator() {
        Pizza a = new SliceDecorator(new BakeDecorator(new HamDecorator(new JalapenoDecorator(new BasePizza())), "Normal"), 4);
        Pizza b = new SliceDecorator(new BakeDecorator(new JalapenoDecorator(new HamDecorator(new BasePizza())), "Normal"), 6);

        assertEquals(3. / 4., PizzaRater.pizzaScore(a, b), DELTA);
    }
}
