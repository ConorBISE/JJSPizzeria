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
        Order order = new Order(Arrays.asList("cheese", "tomato", "olives"), 6, "crispy");
        Personality personality = new HappyDialogue();
        Customer customer = new Customer("Jane", order, personality);


        String expectedDetails = "a pizza with cheese, tomato, olives cut into 6 slices and baked crispy";
        assertEquals(expectedDetails, customer.getOrder().getOrderDetails());
    }

    @Test
    public void testLoadCustomers() throws IOException{
        String resourcePath =   "/org/jjspizzeria/jjspizzeria/customers.json";
        List<Customer> customers = Customer.loadCustomers(resourcePath);

        // Test that the list of customers is loaded correctly
        assertEquals(10, customers.size());

        // Test the first customer
        Customer firstCustomer = customers.get(0);
        assertEquals("John Smith",firstCustomer.getName());
        assertTrue(firstCustomer.getPersonality() instanceof HappyDialogue);
        assertEquals(6, firstCustomer.getOrder().getSlices());
        assertEquals("Normal",firstCustomer.getOrder().getBakingPreference());

        // Test the last customer
        Customer lastCustomer = customers.get(9);
        assertEquals("Kevin", lastCustomer .getName());
        assertTrue(lastCustomer .getPersonality() instanceof AngryDialogue);
        assertEquals(8, lastCustomer .getOrder().getSlices());
        assertEquals("Normal", lastCustomer.getOrder().getBakingPreference());
    }


    @Test
    public void testHappyDialogue() {
        Order order = new Order(Arrays.asList("cheese", "tomato"), 8, "crispy");
        Personality happyPersonality = new HappyDialogue();
        Customer customer = new Customer("John", order, happyPersonality);

        assertEquals("Hi there! I'm John. I would love to order a pizza with cheese, tomato cut into 8 slices and baked crispy!", happyPersonality.greetingDialogue(customer));
        assertEquals("Wow, this pizza looks amazing! I can't wait to dig in!", happyPersonality.ratingDialogue(customer));
        assertEquals("Thanks so much for the pizza! Have a great day!! Goodbye :) ", happyPersonality.leavingDialogue(customer));
    }

    @Test
    public void testSelectCustomer() throws IOException {

        DayCreator dayCreator = new DayCreator(1);

        List<Customer> day1Customers = dayCreator.selectCustomer();
        assertEquals(1, day1Customers.size(), "Day 1 should only have 1 customer");
        assertEquals("John Smith", day1Customers.get(0).getName(), "Day 1 customer should be John Smith");

        // Testing Day 2 (should return exactly 3 unique random customers)
        dayCreator.setDay(2);
        List<Customer> day2Customers = dayCreator.selectCustomer();
        assertEquals(3, day2Customers.size(), "Day 2 should have 3 customers");

    }

}
