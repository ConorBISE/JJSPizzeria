package org.jjspizzeria.jjspizzeria.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.BakeDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.BasePizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.HamDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.JalapenoDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.PizzaDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.SliceDecorator;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Topping;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.ToppingDecorator;

public class PizzaRater {

    @SuppressWarnings("unchecked")
    private static <T extends PizzaDecorator> List<T> getPizzaDecorators(Pizza pizza, Class<T> clazz) {
        Pizza current = pizza;
        List<T> list = new ArrayList<>();

        while (current instanceof PizzaDecorator) {
            PizzaDecorator currentDecorator = (PizzaDecorator) current;
    
            if (clazz.isAssignableFrom(currentDecorator.getClass())) {
                // Type safety: we've just made sure this decorator is an instance of T
                // (at least at compile time)
                list.add((T) currentDecorator);
            }
                
            current = currentDecorator.getPizza();
        }

        return list;
    }

    public static double pizzaScore(Pizza ordered, Pizza made) {
        int mistakes = 0;

        // Count mistakes in toppings
        List<Topping> orderedToppings = getPizzaDecorators(ordered, ToppingDecorator.class)
            .stream()
            .map(d -> d.getTopping())
            .toList();

        List<Topping> madeToppings = getPizzaDecorators(made, ToppingDecorator.class)
            .stream()
            .map(d -> d.getTopping())
            .toList();


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

        // Did the user slice or bake wrong?
        List<SliceDecorator> orderedSlice = getPizzaDecorators(ordered, SliceDecorator.class);
        List<SliceDecorator> madeSlice = getPizzaDecorators(made, SliceDecorator.class);

        if (!orderedSlice.equals(madeSlice)) {
            mistakes += 1;
        }

        List<BakeDecorator> orderedBake = getPizzaDecorators(ordered, BakeDecorator.class);
        List<BakeDecorator> madeBake = getPizzaDecorators(made, BakeDecorator.class);

        if (!orderedBake.equals(madeBake)) {
            mistakes += 1;
        }

        int orderComplexity = getPizzaDecorators(ordered, PizzaDecorator.class).size();

        // Turn the number of mistakes into a score between 0 - 1
        // We do this by dividing by the number of decorators in the order chain (as a rough
        // measure of how complex the order is), and clamping
        return Math.max(Math.min((double)mistakes / (double)orderComplexity, 1.), 0.);
    }
}
