package org.jjspizzeria.jjspizzeria.customer;

public class HappyDialogue implements Personality {
    @Override
    public String greetingDialogue(String name, String orderDetails) {
        return "Hi there! I'm " + name + ". I would love to order " + orderDetails + "!";
    }

    @Override
    public String ratingDialogue() {
        return "Wow, this pizza looks amazing! I can't wait to dig in!";
    }

    @Override
    public String leavingDialogue() {
        return "Thanks so much for the pizza! Have a great day!! Goodbye :) ";
    }
}
