package org.jjspizzeria.jjspizzeria.customer;

public class AngryDialogue implements Personality{
    @Override
    public String greetingDialogue(Customer customer) {
        return "Ugh, I'm obviously " + customer.getName() + " .If your actually able to make a pizza then I'll order " + customer.getOrder().getOrderDetails();
    }

    @Override
    public String ratingDialogue(Customer customer) {
        return "This pizza looks kinda shit. Does it always look like this?!";
    }

    @Override
    public String leavingDialogue(Customer customer) {
        return "This is the last time that I come here!";
    }
}
