package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.*;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;
import org.jjspizzeria.jjspizzeria.GameConsole;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import javafx.application.Platform;

public class PizzaManagerTest {
    private PizzaManager pizzaManager;
    private GameConsole mockConsole;

    static {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
        }
    }

    @Before
    public void setUp() {
        PizzaManager.resetInstance();

        mockConsole = mock(GameConsole.class);
        GameConsole.setTestInstance(mockConsole);

        pizzaManager = PizzaManager.getInstance();
        pizzaManager.reset();
    }

    @Test
    public void testSingleton() {
        PizzaManager instance1 = PizzaManager.getInstance();
        PizzaManager instance2 = PizzaManager.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testAddTopping() {
        ToppingDecorator cheese = new CheeseDecorator();
        pizzaManager.addTopping(cheese);
        assertTrue(pizzaManager.getPizza() instanceof CheeseDecorator);
    }

    @Test
    public void testAddDuplicateTopping() {
        ToppingDecorator cheese1 = new CheeseDecorator();
        ToppingDecorator cheese2 = new CheeseDecorator();
        pizzaManager.addTopping(cheese1);
        pizzaManager.addTopping(cheese2);
        verify(mockConsole).append("You already have Cheese on the Pizza!");
    }

    @Test
    public void testRemoveTopTopping() {
        ToppingDecorator cheese = new CheeseDecorator();
        pizzaManager.addTopping(cheese);
        pizzaManager.removeTopTopping();
        assertFalse(pizzaManager.getPizza() instanceof CheeseDecorator);
    }

    @Test
    public void testRemoveNoToppings() {
        pizzaManager.removeTopTopping();
        verify(mockConsole).append("No toppings to remove.");
    }

    @Test
    public void testBakePizza() {
        pizzaManager.bakePizza("crispy");
        assertEquals(PizzaState.BAKED, pizzaManager.getPizzaState());
    }

    @Test
    public void testSlicePizzaBeforeBaking() {
        pizzaManager.slicePizza(6);
        verify(mockConsole).append("You can't slice the pizza right now!");
    }

    @Test
    public void testSlicePizzaAfterBaking() throws InterruptedException {
        pizzaManager.bakePizza("crispy");
        Thread.sleep(1500);
        pizzaManager.slicePizza(6);
        assertEquals(PizzaState.SLICED, pizzaManager.getPizzaState());
    }

    @Test
    public void testBoxPizzaBeforeSlicing() {
        pizzaManager.bakePizza("crispy");
        pizzaManager.boxPizza();
        verify(mockConsole).append("You can't box the pizza until it's been sliced!");
    }

    @Test
    public void testBoxPizzaAfterSlicing() throws InterruptedException {
        pizzaManager.bakePizza("crispy");
        Thread.sleep(1500);
        pizzaManager.slicePizza(6);
        pizzaManager.boxPizza();
        assertEquals(PizzaState.BOXED, pizzaManager.getPizzaState());
    }
}


