package org.jjspizzeria.jjspizzeria.pizza.pizzaDecorator;


public class DecoratorMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");




        //Add toppings to pizza
        Pizza pizzaWithCheese = new CheeseDecorator(new BasePizza());
        Pizza pizzaWithPepperoni = new CheeseDecorator(pizzaWithCheese);

        //Print Pizza info
        System.out.println("Description: " + pizzaWithPepperoni.getDescription());
        System.out.println("Cost: " + "$" + pizzaWithPepperoni.getCost());



    }
}