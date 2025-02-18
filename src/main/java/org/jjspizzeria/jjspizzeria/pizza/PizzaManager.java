package org.jjspizzeria.jjspizzeria.pizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzaDecorator.*;

import org.jjspizzeria.jjspizzeria.GameConsole;

import java.util.Timer;
import java.util.TimerTask;

public class PizzaManager {

    private static PizzaManager instance;
    private Pizza pizza; // Top of the decorator chain
    private PizzaState state;
    private Timer bakingTimer;
    private final GameConsole gameConsole;

    private PizzaManager() {
        this.pizza = new BasePizza();
        this.state = PizzaState.UNBAKED;
        this.gameConsole = GameConsole.getInstance();
    }

    /**
     * Returns the sole instance of PizzaManager.
     */
    public static synchronized PizzaManager getInstance() {
        if (instance == null) {
            instance = new PizzaManager();
        }
        return instance;
    }

    public Pizza getPizza() {
        return pizza;
    }

    /**
     * Scans the current decorator chain to see if we already have an instance of the same decorator class.
     * This ensures each topping is present only once.
     */
    private boolean hasTopping(Class<? extends ToppingDecorator> decoratorClass) {
        Pizza current = pizza;
        while (current instanceof ToppingDecorator) {
            if (decoratorClass.isInstance(current)) {
                return true;
            }
            current = ((ToppingDecorator) current).getPizza();
        }
        return false;
    }


    /**
     * Adds a topping decorator to the current pizza, but only when the pizza is UNBAKED.
     */
    public void addTopping(ToppingDecorator toppingDecorator) {
        if (state == PizzaState.UNBAKED) {

            // Check if we already have a decorator of this exact class
            Class<? extends ToppingDecorator> decoratorClass = toppingDecorator.getClass();
            if (hasTopping(decoratorClass)) {
                gameConsole.append("You already have " + toppingDecorator.getTopping().getName() + " on the Pizza!");
                return;
            }

            // Attach the new topping
            toppingDecorator.setPizza(pizza);
            this.pizza = toppingDecorator;
            gameConsole.append("Added " + toppingDecorator.getTopping().getName() + " to the Pizza!");

            // TODO: add the topping image to the pizza
        } else {
            gameConsole.append("You can't add toppings anymore!");
        }
    }

    /**
     * Removes the topmost topping from the pizza, if allowed.
     */
    public void removeTopTopping() {
        if (state == PizzaState.UNBAKED) {
            if (pizza instanceof ToppingDecorator top) {
                // 'un-decorate' by returning the wrapped pizza
                gameConsole.append("Removing " + top.getTopping().getName() + " from the Pizza!");
                this.pizza = top.getPizza();

                // TODO: remove top png
            } else {
                gameConsole.append("No toppings to remove.");
            }
        } else {
            gameConsole.append("You can't remove toppings anymore!");
        }
    }

    /**
     * Start baking the pizza.
     * Baking transitions from UNBAKED -> BAKING, and after a timer it goes to BAKED.
     */
    public void bakePizza(int bakingTimeSeconds) {
        if (state != PizzaState.UNBAKED) {
            gameConsole.append("The Pizza has already been baked!");
            return;
        }

        gameConsole.append("Baking pizza for " + bakingTimeSeconds + " seconds...");
        state = PizzaState.BAKING;

        // TODO: refactor this cause idk
        // Create a Timer to simulate baking
        bakingTimer = new Timer();
        bakingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // After the specified delay, move to BAKED state
                state = PizzaState.BAKED;
                gameConsole.append("Pizza is now BAKED!");
            }
        }, bakingTimeSeconds * 1000L);
    }

    /**
     * Once the pizza is in BAKED state, you can slice it.
     */
    public void slicePizza() {
        if (state == PizzaState.BAKED) {
            state = PizzaState.SLICED;
            // TODO: overlay a "slice.png" or do any slicing logic
            gameConsole.append("Pizza has been sliced.");
        } else {
            gameConsole.append("You can't slice the pizza right now!");
        }
    }

    /**
     * Once the pizza is in BAKED or SLICED state, you can box it.
     */
    public void boxPizza() {
        if (state == PizzaState.SLICED) {
            state = PizzaState.BOXED;
            // TODO: overlay a "box.png" or do final packaging logic
            gameConsole.append("Pizza has been boxed! Please hand the pizza over to the customer");
        } else {
            gameConsole.append("You can't box the pizza until it's been sliced!");
        }
    }

}
