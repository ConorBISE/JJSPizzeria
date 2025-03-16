package org.jjspizzeria.jjspizzeria.customer;

public class AngryDialogue implements Personality{
    @Override
    public String greetingDialogue(String name, String orderDetails) {
        return "Ugh, I'm " + name +
                ". If you're actually able to make a pizza, then I'll order " + orderDetails + ".";

    }

    @Override
    public String ratingDialogue() {
        return "This pizza looks awful. Does it always look like this?!";
    }

    @Override
    public String leavingDialogue() {
        return "This is the last time that I come here!";
    }
}
