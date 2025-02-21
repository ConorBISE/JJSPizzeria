package org.jjspizzeria.jjspizzeria.customer;

public interface Personality {
    String greetingDialogue(Customer customer);
    String ratingDialogue(Customer customer);
    String leavingDialogue(Customer customer);
}
