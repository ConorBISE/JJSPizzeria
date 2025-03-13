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
        BakeDecorator bakeDecorator = new BakeDecorator("crispy");
        pizzaManager.bakePizza(bakeDecorator);
        assertEquals(PizzaState.BAKED, pizzaManager.getPizzaState());
    }

    @Test
    public void testSlicePizzaAfterBaking() throws InterruptedException {
        BakeDecorator bakeDecorator = new BakeDecorator("crispy");
        pizzaManager.bakePizza(bakeDecorator);
        Thread.sleep(1500);
        SliceDecorator sliceDecorator = new SliceDecorator(6);
        pizzaManager.slicePizza(sliceDecorator);
        assertEquals(PizzaState.SLICED, pizzaManager.getPizzaState());
    }

    @Test
    public void testBoxPizzaBeforeSlicing() {
        BakeDecorator bakeDecorator = new BakeDecorator("crispy");
        pizzaManager.bakePizza(bakeDecorator);
        BoxDecorator boxDecorator = new BoxDecorator();
        pizzaManager.boxPizza(boxDecorator);
        verify(mockConsole).append("You can't box the pizza until it's been sliced!");
    }

    @Test
    public void testBoxPizzaAfterSlicing() throws InterruptedException {
        BakeDecorator bakeDecorator = new BakeDecorator("crispy");
        pizzaManager.bakePizza(bakeDecorator);
        Thread.sleep(1500);
        SliceDecorator sliceDecorator = new SliceDecorator(6);
        pizzaManager.slicePizza(sliceDecorator);
        BoxDecorator boxDecorator = new BoxDecorator();
        pizzaManager.boxPizza(boxDecorator);
        assertEquals(PizzaState.BOXED, pizzaManager.getPizzaState());
    }
}

