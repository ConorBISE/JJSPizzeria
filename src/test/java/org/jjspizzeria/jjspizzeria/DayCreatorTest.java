package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.customer.Customer;
import org.jjspizzeria.jjspizzeria.customer.DayCreator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayCreatorTest {

    @Test
    public void testSelectCustomer() {

        DayCreator dayCreator = new DayCreator(1);

        List<Customer> day1Customers = dayCreator.selectCustomer();
        assertEquals(1, day1Customers.size(), "Day 1 should only have 1 customer");
        assertEquals("John Smith", day1Customers.get(0).getName(), "Day 1 customer should be John Smith");

        // Testing Day 2 (should return exactly 3 unique random customers)
        dayCreator.setDay(2);
        List<Customer> day2Customers = dayCreator.selectCustomer();
        assertEquals(3, day2Customers.size(), "Day 2 should have 3 customers");

    }

    @Test
    public void testRegularPricing() {
        DayCreator dayCreator = new DayCreator(1);
        double basePrice = 25.80;
        double finalPrice = dayCreator.calculatePrice(basePrice);
        assertEquals(basePrice, finalPrice);
    }

    @Test
    public void testMidweekPricing() {
        DayCreator dayCreator = new DayCreator(2);
        double basePrice = 13.50;
        double finalPrice = dayCreator.calculatePrice(basePrice);
        double expectedPrice = 13.50 * 0.80;
        assertEquals(expectedPrice, finalPrice);
    }

    @Test
    public void testPricingChange() {
        ///////////DAY 1/////////////////////
        DayCreator dayCreator = new DayCreator(1);
        double basePrice = 11.16;
        double finalPrice = dayCreator.calculatePrice(basePrice);

        ///////////DAY 2/////////////////////
        dayCreator.setDay(2);
        double newBasePrice = 20;
        double newFinalPrice = dayCreator.calculatePrice(newBasePrice);
        double expectedPrice = 16;
        assertEquals(basePrice, finalPrice);
        assertEquals(expectedPrice, newFinalPrice);

    }

}
