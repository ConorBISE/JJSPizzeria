package org.jjspizzeria.jjspizzeria.customer;

public class NeutralDialogue implements Personality{
    @Override
    public String greetingDialogue(String name, String orderDetails) {
        return "Hello, I'm " + name +
                ". I'd like to order a pizza with " +
               orderDetails + ".";
    }

    @Override
    public String ratingDialogue() {
        return "The pizza looks alright. Sound";
    }

    @Override
    public String leavingDialogue() {
        return "Goodbye";
    }
}
