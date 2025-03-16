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
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.MidWeekPricing;
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.PriceCalculator;
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.PricingStrategy;
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.RegularPricing;

import java.util.List;


public class GameScreen extends Screen implements PizzaObserver {
    private int currentDay = 1;
    private DayCreator dayCreator;
    private Customer currentCustomer;
    private List<Customer> dailyCustomers;
    private int currentCustomerIndex = 0;

    private GameState currentState = GameState.DAY_START;

    private PricingStrategy pricingStrategy;
    private PriceCalculator priceCalculator;

    public GameScreen() {
        super("/org/jjspizzeria/jjspizzeria/layouts/game.fxml");
        GameConsole.getInstance().append("Welcome to JJ's Pizzeria!");
        this.dayCreator = new DayCreator(currentDay);
        PizzaManager.getInstance().addObserver(this); // Register observer

        setPricingStrategy();
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
    public void startDay() {
        GameConsole.getInstance().append("Day " + currentDay + " begins!");


        // Get customers for the day
        dailyCustomers = dayCreator.selectCustomer();
        currentCustomerIndex = 0;

        // Move to next state
        currentState = GameState.CUSTOMER_GREETING;
        progressGame();
    }

    //Greets the next customer in line

    public void greetNextCustomer() {
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

        if (state == PizzaState.FINISHED) {
            GameConsole.getInstance().append("Pizza is finished! Now rating the order...");
            currentState = GameState.RATING_PIZZA;
            progressGame();
        }
    }


    //Compares the made pizza with the customer's order and displays feedback
    public void processPizzaRating() {
        Pizza orderedPizza = createPizzaFromCustomer(currentCustomer);
        Pizza madePizza = PizzaManager.getInstance().getPizza();

        double score = PizzaRater.pizzaScore(orderedPizza, madePizza);
        double basePrice = 10.0;
        double finalPrice = priceCalculator.calculatePrice(basePrice);

        GameConsole.getInstance().append(dayCreator.receiveRatings(currentCustomer));
        GameConsole.getInstance().append("Rating score: " + String.format("%.2f", score * 100) + "%");
        GameConsole.getInstance().append("The price of the pizza is €" + finalPrice );
        // Move to customer leaving state
        currentState = GameState.CUSTOMER_LEAVING;
        progressGame();
    }



    // Handles customer departure and advances to next customer
    public void handleCustomerLeaving() {
        GameConsole.getInstance().append(dayCreator.customersLeave(currentCustomer));

        // Move to next customer
        currentCustomerIndex++;
        currentState = GameState.CUSTOMER_GREETING;
        progressGame();
    }

    //Ends the current day and advances to next day
    public void endDay() {
        GameConsole.getInstance().append("Day " + currentDay + " is over!");
        currentDay++;

        if (currentDay <= 3) { // Game lasts 3 days
            dayCreator.setDay(currentDay);
            setPricingStrategy();
            currentState = GameState.DAY_START;
            progressGame();
        } else {
            currentState = GameState.GAME_OVER;
            progressGame();
        }
    }

    //Ends the game
    public void endGame() {
        GameConsole.getInstance().append("Game Over! Thanks for playing.");
    }

    //Converts a Customer's toppings, slices, and baking preference( which is specified
    // in the customers json file and extracted in customers) into a Pizza object.
    public Pizza createPizzaFromCustomer(Customer customer) {
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


    public void setPricingStrategy(){
        if(currentDay < 3){
            //day 1 and 2 will have regular pricing
            pricingStrategy = new RegularPricing();
        } else{ //day 3 will have ,midweek pricing (20% discount)
        GameConsole.getInstance().append("Its now mid week so all our pizzas are at 20% discount. WOOHOO!!");
        pricingStrategy = new MidWeekPricing();
    }
     priceCalculator = new PriceCalculator(pricingStrategy);
    }


}