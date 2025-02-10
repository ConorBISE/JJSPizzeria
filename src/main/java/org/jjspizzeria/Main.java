package org.jjspizzeria;

import org.jjspizzeria.pizza.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        Pizza myPizza = new HamDecorator(new JalapenoDecorator(new CheeseDecorator(new ThinCrustPizza())));
        System.out.println("Desription: " + myPizza.getDescription());
        System.out.println("Cost: " + "$" + myPizza.getCost());


    }
}