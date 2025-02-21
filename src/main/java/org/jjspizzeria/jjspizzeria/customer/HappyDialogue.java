package org.jjspizzeria.jjspizzeria.customer;

public class HappyDialogue implements Personality {
    @Override
    public String greetingDialogue(Customer customer) {
        return "Hi there! I'm " + customer.getName() + ". I would love to order " + customer.getOrder().getOrderDetails() + "!";
    }

    @Override
    public String ratingDialogue(Customer customer) {
        return "Wow, this pizza looks amazing! I can't wait to dig in!";
    }

    @Override
    public String leavingDialogue(Customer customer) {
        return "Thanks so much for the pizza! Have a great day!! Goodbye :) ";
    }
}
