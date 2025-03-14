package org.jjspizzeria.jjspizzeria.customer;

public interface  Personality{
    String greetingDialogue(String name, String orderDetails);
    String ratingDialogue();
    String leavingDialogue();
}