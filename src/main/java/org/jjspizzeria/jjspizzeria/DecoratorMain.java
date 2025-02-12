package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.*;


public class DecoratorMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        //Create Toppings
        ToppingEntity cheese = new ToppingEntity("Cheese", 1.00, "cheese.png");
        ToppingEntity jalapeno = new ToppingEntity("Jalapeno", 1.50, "jalapeno.png");
        ToppingEntity ham = new ToppingEntity("Ham", 2.00, "ham.png");

        //Add toppings to pizza
        Pizza pizzaWithCheese = new ConcreteTopping(new CheeseCrustPizza(), cheese);
        Pizza pizzaWithJalapeno = new ConcreteTopping(pizzaWithCheese, jalapeno);

        //Print Pizza info
        System.out.println("Description: " + pizzaWithJalapeno.getDescription());
        System.out.println("Cost: " + "$" + pizzaWithJalapeno.getCost());



    }
}