package org.jjspizzeria.jjspizzeria.pizza;

import java.util.ArrayList;
import java.util.List;

import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.BasePizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.HamDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.JalapenoDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.PineappleDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Topping;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.ToppingDecorator;

public class PizzaRater {

    private static List<Topping> getPizzaToppings(Pizza pizza) {        
        Pizza current = pizza;
        List<Topping> list = new ArrayList<>();

        while (current instanceof ToppingDecorator) {
            ToppingDecorator currentDecorator = (ToppingDecorator) current;
            list.add(currentDecorator.getTopping());
            current = currentDecorator.getPizza();
        }

        return list;
    }

    public static double pizzaScore(Pizza ordered, Pizza made) {
        List<Topping> orderedToppings = getPizzaToppings(ordered);
        List<Topping> madeToppings = getPizzaToppings(made);

        int mistakes = 0;

        for (Topping madeTopping : madeToppings) {
            // Was this topping not on the pizza the user requested?
            if (!orderedToppings.contains(madeTopping))
                mistakes += 1;
        }

        for (Topping orderedTopping : orderedToppings) {
            // Was this topping not on the pizza the user requested?
            if (!madeToppings.contains(orderedTopping))
                mistakes += 1;
        }

        // Turn the number of mistakes into a score between 0 - 1
        // We do this by dividing by the number of toppings in the order (as a rough
        // measure of how complex the order is), and clamping
        return Math.max(Math.min((double)mistakes / (double)orderedToppings.size(), 1.), 0.);
    }
}
