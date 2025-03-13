package org.jjspizzeria.jjspizzeria.customer;

public class NeutralDialogue implements Personality{
    @Override
    public String greetingDialogue(Customer customer) {
        return "\nHello, I'm " + customer.getName() + ". I'd like to order " + customer.getOrder().getOrderDetails() + ".";
    }

    @Override
    public String ratingDialogue(Customer customer) {
        return "\nThe pizza looks alright. Sound";
    }

    @Override
    public String leavingDialogue(Customer customer) {
        return "\nGoodbye";
    }
}
