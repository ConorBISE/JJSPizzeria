package org.jjspizzeria.jjspizzeria.pizza;

public enum PizzaState {
    /**
     * This class describes the enums used to manage the state of a pizza.
     */
    UNBAKED,   // starting state: can add or remove toppings
    BAKING,    // pizza is in the oven, timer is running
    BAKED,     // done baking, can slice or box
    SLICED,    // after slicing
    BOXED,      // sitting in a box on the counter ready to go
    FINISHED
}