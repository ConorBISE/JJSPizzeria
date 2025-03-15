package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.customer.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;

public class CustomerTest {

    @Test
    public void testGetOrderDetails() {
        Personality personality = new HappyDialogue();
        List<String> toppings = Arrays.asList("cheese", "tomato", "olives");
        Customer customer = new Customer("Jane", toppings, 6, "crispy", personality);

        String expectedDetails = "a pizza with cheese, tomato, olives cut into 6 slices and baked crispy";
        assertEquals(expectedDetails, customer.getOrderDetails());
    }

    @Test
    public void testLoadCustomers() throws IOException {
        String resourcePath = "/org/jjspizzeria/jjspizzeria/customers.json";
        List<Customer> customers = Customer.loadCustomers(resourcePath);

        // testing that the list of customers is loaded correctly
        assertEquals(10, customers.size());

        // testing the first customer
        Customer firstCustomer = customers.get(0);
        assertEquals("John Smith", firstCustomer.getName());
        assertTrue(firstCustomer.getPersonality() instanceof HappyDialogue);
        assertEquals(6, firstCustomer.getSlices());
        assertEquals("Normal", firstCustomer.getBakingPreference());

        // testing the last customer
        Customer lastCustomer = customers.get(9);
        assertEquals("Kevin", lastCustomer.getName());
        assertTrue(lastCustomer.getPersonality() instanceof AngryDialogue);
        assertEquals(8, lastCustomer.getSlices());
        assertEquals("Normal", lastCustomer.getBakingPreference());
    }

    @Test
    public void testHappyDialogue() {
        List<String> toppings = Arrays.asList("cheese", "tomato");
        Personality happyPersonality = new HappyDialogue();
        Customer customer = new Customer("John", toppings, 8, "crispy", happyPersonality);

        String expectedOrderDetails = customer.getOrderDetails();
        assertEquals("Hi there! I'm John. I would love to order " + expectedOrderDetails + "!",
                happyPersonality.greetingDialogue(customer.getName(), expectedOrderDetails));

        assertEquals("Wow, this pizza looks amazing! I can't wait to dig in!",
                happyPersonality.ratingDialogue());

        assertEquals("Thanks so much for the pizza! Have a great day!! Goodbye :) ",
                happyPersonality.leavingDialogue());
    }
}