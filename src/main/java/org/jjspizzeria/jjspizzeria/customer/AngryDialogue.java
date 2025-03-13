package org.jjspizzeria.jjspizzeria.customer;

public class AngryDialogue implements Personality{
    @Override
    public String greetingDialogue(Customer customer) {
        return "\nUgh, I'm obviously " + customer.getName() + " .If your actually able to make a pizza then I'll order " + customer.getOrder().getOrderDetails();
    }

    @Override
    public String ratingDialogue(Customer customer) {
        return "\nThis pizza looks awful. Does it always look like this?!";
    }

    @Override
    public String leavingDialogue(Customer customer) {
        return "\nThis is the last time that I come here!";
    }
}
