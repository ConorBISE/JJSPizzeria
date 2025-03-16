package org.jjspizzeria.jjspizzeria.pizza;
import org.jjspizzeria.jjspizzeria.pizza.observer.PizzaObserver;
import org.jjspizzeria.jjspizzeria.pizza.observer.Subject;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

import org.jjspizzeria.jjspizzeria.GameConsole;

import java.util.ArrayList;
import java.util.Timer;
import java.util.List;

public class PizzaManager implements Subject {

    private static PizzaManager instance;
    private Pizza pizza; // Top of the decorator chain
    private PizzaState state;
    private Timer bakingTimer;
    private final GameConsole gameConsole;

    private final List<PizzaObserver> observers = new ArrayList<>();

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

    /**
     * Resets the singleton instance - used for testing
     */
    public static void resetInstance() {
        instance = null;
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
        while (current instanceof ToppingDecorator currentToppingDecorator) {
            if (decoratorClass.isInstance(current)) {
                return true;
            }
            current = currentToppingDecorator.getPizza();
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
            notifyObservers();
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
                notifyObservers();

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
    public void bakePizza(BakeDecorator bakeDecorator) {
        if (state != PizzaState.UNBAKED) {
            gameConsole.append("The Pizza has already been baked!");
            return;
        }
        // Wrap the current pizza in the BakeDecorator.
        bakeDecorator.setPizza(pizza);
        pizza = bakeDecorator;

        gameConsole.append("Baking the pizza " + bakeDecorator.getBakeType() + " style!");

        state = PizzaState.BAKING;
        notifyObservers();

        state = PizzaState.BAKED;
        notifyObservers();
    }

    /**
     * Once the pizza is in BAKED state, you can slice it.
     */
    public void slicePizza(SliceDecorator sliceDecorator) {
        if (state == PizzaState.BAKED) {
            // Wrap the current pizza in the SliceDecorator.
            sliceDecorator.setPizza(pizza);
            pizza = sliceDecorator;

            state = PizzaState.SLICED;
            notifyObservers();

            gameConsole.append("Pizza has been sliced.");
        } else {
            gameConsole.append("You can't slice the pizza right now!");
        }
    }

    /**
     * Once the pizza is in BAKED or SLICED state, you can box it.
     */
    public void boxPizza(BoxDecorator boxDecorator) {
        if (state == PizzaState.SLICED) {
            // Wrap the current pizza in the BoxDecorator.
            boxDecorator.setPizza(pizza);
            pizza = boxDecorator;

            state = PizzaState.BOXED;
            notifyObservers();

            gameConsole.append("Pizza has been boxed! Please hand the pizza over to the customer");
        } else {
            gameConsole.append("You can't box the pizza until it's been sliced!");
        }
    }

    public void finishPizza() {
        if (state == PizzaState.BOXED) {
            state = PizzaState.FINISHED;
            notifyObservers();
        }
    }

    public PizzaState getPizzaState(){
        return this.state;
    }

    public void reset() {
        this.pizza = new BasePizza();
        this.state = PizzaState.UNBAKED;
        if (this.bakingTimer != null) {
            this.bakingTimer.cancel();
            this.bakingTimer = null;
        }
    }



    @Override
    public void addObserver(PizzaObserver observer) {
        observers.add(observer);
        // Put this here so some buttons are disabled from the start
        // TODO: check if there is a better place for this?
        notifyObservers();
    }

    @Override
    public void removeObserver(PizzaObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PizzaObserver observer : observers) {
            observer.onPizzaChanged(pizza, state);
        }
    }
}
