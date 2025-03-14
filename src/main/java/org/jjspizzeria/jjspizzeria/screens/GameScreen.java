package org.jjspizzeria.jjspizzeria.screens;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.customer.Customer;
import org.jjspizzeria.jjspizzeria.customer.DayCreator;
import org.jjspizzeria.jjspizzeria.framework.Screen;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.PizzaRater;
import org.jjspizzeria.jjspizzeria.pizza.PizzaState;
import org.jjspizzeria.jjspizzeria.pizza.observer.PizzaObserver;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

import java.util.List;

public class GameScreen extends Screen implements PizzaObserver {
    private int currentDay = 1;
    private DayCreator dayCreator;
    private Customer currentCustomer;
    private List<Customer> dailyCustomers;
    private int currentCustomerIndex = 0;

    private GameState currentState = GameState.DAY_START;

    public GameScreen() {
        super("/org/jjspizzeria/jjspizzeria/layouts/game.fxml");
        GameConsole.getInstance().append("Welcome to JJ's Pizzeria!");
        this.dayCreator = new DayCreator(currentDay);
        PizzaManager.getInstance().addObserver(this); // Register observer

        // Start the first day
        progressGame();
    }

    // Main game flow controller - advances the game based on current state

    public void progressGame() {
        switch (currentState) {
            case DAY_START:
                startDay();
                break;
            case CUSTOMER_GREETING:
                greetNextCustomer();
                break;
            case MAKING_PIZZA:
                // Nothing to do here - waiting for user to make pizza
                // UI buttons will control this phase through PizzaManager
                break;
            case RATING_PIZZA:
                processPizzaRating();
                break;
            case CUSTOMER_LEAVING:
                handleCustomerLeaving();
                break;
            case DAY_END:
                endDay();
                break;
            case GAME_OVER:
                endGame();
                break;
        }
    }

    //Starts a new day and prepares customers

    private void startDay() {
        GameConsole.getInstance().append("Day " + currentDay + " begins!");

        // Get customers for the day
        dailyCustomers = dayCreator.selectCustomer();
        currentCustomerIndex = 0;
        System.out.println("Customers selected for the day: " + dailyCustomers.size());

        // Move to next state
        currentState = GameState.CUSTOMER_GREETING;
        progressGame();
    }

    //Greets the next customer in line

    private void greetNextCustomer() {
        if (currentCustomerIndex < dailyCustomers.size()) {
            currentCustomer = dailyCustomers.get(currentCustomerIndex);
            GameConsole.getInstance().append(dayCreator.greetCustomers(currentCustomer));

            // Reset pizza manager for new order
            PizzaManager.getInstance().reset();
            GameConsole.getInstance().append("[Make the pizza according to the customer's order]");

            // Now wait for user to make pizza
            currentState = GameState.MAKING_PIZZA;

            // The onPizzaChanged observer will trigger when pizza is boxed
        } else {
            // No more customers today
            currentState = GameState.DAY_END;
            progressGame();
        }
    }

    //This method is triggered when the pizza state changes via observer pattern

    @Override
    public void onPizzaChanged(Pizza pizza, PizzaState state) {
        System.out.println("onPizzaChanged() called - Current Pizza State: " + state);
        if (state == PizzaState.BOXED) {
            GameConsole.getInstance().append("Pizza is boxed! Now rating the order...");

            currentState = GameState.RATING_PIZZA;
            progressGame();
        }
    }


    //Compares the made pizza with the customer's order and displays feedback
    private void processPizzaRating() {
        Pizza orderedPizza = createPizzaFromCustomer(currentCustomer);
        Pizza madePizza = PizzaManager.getInstance().getPizza();
        double score = PizzaRater.pizzaScore(orderedPizza, madePizza);

        GameConsole.getInstance().append("Rating score: " + score);
        GameConsole.getInstance().append(dayCreator.receiveRatings(currentCustomer));
        // Move to customer leaving state
        currentState = GameState.CUSTOMER_LEAVING;
        progressGame();
    }

    // Handles customer departure and advances to next customer
    private void handleCustomerLeaving() {
        GameConsole.getInstance().append(dayCreator.customersLeave(currentCustomer));

        // Move to next customer
        currentCustomerIndex++;
        currentState = GameState.CUSTOMER_GREETING;
        progressGame();
    }

    //Ends the current day and advances to next day
    private void endDay() {
        GameConsole.getInstance().append("Day " + currentDay + " is over!");
        currentDay++;

        if (currentDay <= 3) { // Game lasts 3 days
            dayCreator.setDay(currentDay);
            currentState = GameState.DAY_START;
            progressGame();
        } else {
            currentState = GameState.GAME_OVER;
            progressGame();
        }
    }

    //Ends the game
    private void endGame() {
        GameConsole.getInstance().append("Game Over! Thanks for playing.");
    }

    //Converts a Customer's toppings, slices, and baking preference( which is specified
    // in the customers json file and extracted in customers) into a Pizza object.
    private Pizza createPizzaFromCustomer(Customer customer) {
        Pizza pizza = new BasePizza(); // Start with base pizza

        // Add multiple toppings dynamically dependent on the order
        for (String topping : customer.getToppings()) {
            switch (topping.toLowerCase()) {
                case "cheese":
                    pizza = new CheeseDecorator(pizza);
                    break;
                case "tomato":
                    pizza = new TomatoDecorator(pizza);
                    break;
                case "ham":
                    pizza = new HamDecorator(pizza);
                    break;
                case "jalapeno":
                    pizza = new JalapenoDecorator(pizza);
                    break;
                case "pepperoni":
                    pizza = new PepperoniDecorator(pizza);
                    break;
                case "mushroom":
                    pizza = new MushroomDecorator(pizza);
                    break;
                case "pineapple":
                    pizza = new PineappleDecorator(pizza);
                    break;
                case "onion":
                    pizza = new OnionDecorator(pizza);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown topping: " + topping);
            }
        }

        // Apply baking preference
        if (!customer.getBakingPreference().isEmpty()) {
            pizza = new BakeDecorator(pizza, customer.getBakingPreference());
        }

        // Apply slicing
        if (customer.getSlices() > 0) {
            pizza = new SliceDecorator(pizza, customer.getSlices());
        }
        return pizza;
    }

}