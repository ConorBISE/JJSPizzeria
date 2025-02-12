package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.*;

public class DecoratorMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        Topping cheese = new Topping("Cheese", 1.00, "cheese.png");
        Topping jalapeno = new Topping("Jalapeno", 1.50, "jalapeno.png");
        Topping ham = new Topping("Ham", 2.00, "ham.png");

        Pizza pizzaWithCheese = new ConcreteTopping(new CheeseCrustPizza(), cheese);
        Pizza pizzaWithJalapeno = new ConcreteTopping(pizzaWithCheese, jalapeno);

        System.out.println("Description: " + pizzaWithJalapeno.getDescription());
        System.out.println("Cost: " + "$" + pizzaWithJalapeno.getCost());

        // Pizza myPizza = new HamDecorator(new JalapenoDecorator(new CheeseDecorator(new ThinCrustPizza())));
        // System.out.println("Desription: " + myPizza.getDescription());
        // System.out.println("Cost: " + "$" + myPizza.getCost());


    }
}