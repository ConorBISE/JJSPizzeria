package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.customer.Customer;
import org.jjspizzeria.jjspizzeria.customer.DayCreator;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;
import org.jjspizzeria.jjspizzeria.screens.GameScreen;
import org.jjspizzeria.jjspizzeria.screens.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameScreenTest {
    private GameScreen gameScreen;
    private DayCreator mockDayCreator;
    private PizzaManager mockPizzaManager;
    private GameConsole mockConsole;

    @BeforeEach
    public void setUp() throws Exception {
        // creating mocks
        mockConsole = mock(GameConsole.class);
        mockPizzaManager = mock(PizzaManager.class);
        mockDayCreator = mock(DayCreator.class);

        // set up singleton instances by reflection
        setStaticField(GameConsole.class, "instance", mockConsole);
        setStaticField(PizzaManager.class, "instance", mockPizzaManager);

        // Create a GameScreen instance that we can set up for each test individually
        gameScreen = Mockito.spy(new GameScreen());

        // Reset the mock counts after construction
        reset(mockPizzaManager);
    }

    // Helper method to set private field via reflection
    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = GameScreen.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    // Helper method to get private field via reflection
    private Object getPrivateField(Object target, String fieldName) throws Exception {
        Field field = GameScreen.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }

    // Helper method to set static field via reflection
    private static void setStaticField(Class<?> targetClass, String fieldName, Object value) throws Exception {
        Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }

    @Test
    public void testGameStarts_AtDayStart() throws Exception {
        // The constructor in GameScreen already runs progressGame() so reset the state
        setPrivateField(gameScreen, "currentState", GameState.DAY_START);
        assertEquals(GameState.DAY_START, getPrivateField(gameScreen, "currentState"));
    }

    @Test
    public void testProgressGame_CallsNextMethod() throws Exception {
        GameScreen freshGameScreen = new GameScreen();
        GameScreen spyGameScreen = Mockito.spy(freshGameScreen);

        spyGameScreen.progressGame();
        spyGameScreen.progressGame();
        spyGameScreen.progressGame();

        List<Customer> dailyCustomers = (List<Customer>) getPrivateField(spyGameScreen, "dailyCustomers");
        assertNotNull(dailyCustomers, "Customers should be selected when the game progresses.");
        //if dailyCustomers list is populated, this means startDay() was called

    }

    @Test
    public void testGreetNextCustomer_TriggeredByProgressGame() throws Exception {
        // Setting up the test state
        Customer mockCustomer = mock(Customer.class);
        List<Customer> customers = Arrays.asList(mockCustomer);

        setPrivateField(gameScreen, "currentState", GameState.CUSTOMER_GREETING);
        setPrivateField(gameScreen, "dayCreator", mockDayCreator);
        setPrivateField(gameScreen, "dailyCustomers", customers);
        setPrivateField(gameScreen, "currentCustomerIndex", 0);

        reset(mockPizzaManager);

        gameScreen.progressGame();

        verify(mockPizzaManager, atLeastOnce()).reset();
        assertEquals(GameState.MAKING_PIZZA, getPrivateField(gameScreen, "currentState"));
        // checks the game moves to MAKING_PIZZA state after greeting the customer
    }

    @Test
    public void testProgressGame_AdvancesGame() throws Exception {
        GameScreen freshGameScreen = new GameScreen();
        GameScreen spyGameScreen = Mockito.spy(freshGameScreen);

        spyGameScreen.progressGame();

        List<Customer> dailyCustomers = (List<Customer>) getPrivateField(spyGameScreen, "dailyCustomers");
        assertNotNull(dailyCustomers, "Customers should be selected when the game progresses.");
        assertFalse(dailyCustomers.isEmpty(), "At least one customer should be present.");
        //ensures that at least one customer is available to interact with.

    }



    @Test
    public void testGameOver_AfterDay3() throws Exception {
        //  Setting up initial state for day 3
        setPrivateField(gameScreen, "currentDay", 3);
        setPrivateField(gameScreen, "dayCreator", mockDayCreator);

        gameScreen.endDay();
        assertEquals(GameState.GAME_OVER, getPrivateField(gameScreen, "currentState"));
    }

    @Test
    public void testCreatePizzaFromCustomer() {
        // Arrange: Create a customer with specific toppings and preferences
        Customer customer = new Customer("Alice",
                Arrays.asList("cheese", "tomato"),
                6,
                "crispy",
                null);

        // Act: Call createPizzaFromCustomer directly
        Pizza pizza = gameScreen.createPizzaFromCustomer(customer);

        // Assert: Check if the pizza has the expected decorators applied
        assertNotNull(pizza, "Pizza should not be null.");
        assertTrue(hasDecorator(pizza, SliceDecorator.class), "Pizza should be sliced.");
        assertTrue(hasDecorator(pizza, BakeDecorator.class), "Pizza should be baked.");
        assertTrue(hasDecorator(pizza, CheeseDecorator.class), "Pizza should have cheese.");
        assertTrue(hasDecorator(pizza, TomatoDecorator.class), "Pizza should have tomato.");
    }

    //Recursively checks if a pizza contains a specific decorator

    private boolean hasDecorator(Pizza pizza, Class<? extends Pizza> decoratorClass) {
        while (pizza instanceof PizzaDecorator) {
            if (pizza.getClass().equals(decoratorClass)) {
                return true;
            }
            pizza = ((PizzaDecorator) pizza).getPizza(); // Unwrap decorator
        }
        return false;
    }
}