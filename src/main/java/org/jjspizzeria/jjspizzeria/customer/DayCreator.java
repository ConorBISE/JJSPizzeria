package org.jjspizzeria.jjspizzeria.customer;

import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.MidWeekPricing;
import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.PriceCalculator;
import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.PricingStrategy;
import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.RegularPricing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DayCreator {
    private List<Customer> allCustomers;
    private Random random;
    private List<Customer> customersForTheDay;
    private static final String RESOURCE_PATH = "/org/jjspizzeria/jjspizzeria/customers.json";
    private int day;
    private PricingStrategy discountPricing;
    private PricingStrategy regularPricing;

    private PriceCalculator calculator;

    public DayCreator(int day) {
        try {
            this.allCustomers = Customer.loadCustomers(RESOURCE_PATH);
        } catch (IOException e) {
            e.printStackTrace(); // Log the error
            this.allCustomers = new ArrayList<>(); // Default to an empty customer list
        }

        this.random = new Random();
        this.customersForTheDay = new ArrayList<>();
        this.day = day;
        this.discountPricing = new MidWeekPricing();
        this.regularPricing = new RegularPricing();
        this.calculator = new PriceCalculator(this.regularPricing);

    }

    public List<Customer> selectCustomer() {
        customersForTheDay.clear(); //resets customers for a new day

        if (this.day == 1) {
            //so that every player will see this customer on day 1
            customersForTheDay.add(allCustomers.get(0));
            System.out.println("Day 1: Customer added: " + allCustomers.get(0).getName()); // Debug message

        } else {
            //getting 3 unique customers randomly
            List<Customer> availableCustomers = new ArrayList<>(allCustomers);
            for (int i = 0; i < 3 && !availableCustomers.isEmpty(); i++) {
                int randomIndex = random.nextInt(availableCustomers.size());
                customersForTheDay.add(availableCustomers.get(randomIndex));
                availableCustomers.remove(randomIndex); //prevents the same customer being picked on the same day
            }
        }
        return customersForTheDay;

    }


    public String greetCustomers(Customer customer) {
        StringBuilder greetingMessage = new StringBuilder();
        greetingMessage.append(customer.getPersonality().greetingDialogue(customer.getName(), customer.getOrderDetails()));
        return greetingMessage.toString();
    }

    public String receiveRatings(Customer customer) {
        StringBuilder ratingMessage = new StringBuilder();
        ratingMessage.append(customer.getPersonality().ratingDialogue());
        return ratingMessage.toString();
    }

    public String customersLeave(Customer customer) {
        StringBuilder leavingMessage = new StringBuilder();
        leavingMessage.append(customer.getPersonality().leavingDialogue());
        return leavingMessage.toString();
    }

    public double calculatePrice(double basePrice) {
        int[] midWeekDays = {2, 3, 4};
        for (int day : midWeekDays) {
            if (this.day == day) {
                this.calculator.setStrategy(this.discountPricing);
                return this.calculator.calculatePrice(basePrice);
            }
        }
        this.calculator.setStrategy(this.regularPricing);
        return this.calculator.calculatePrice(basePrice);


    }

    public void setDay(int day) {
        this.day = day;
    }
}


