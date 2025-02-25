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

    public DayCreator(int day) throws IOException{
        this.allCustomers = Customer.loadCustomers(RESOURCE_PATH);
        this.random = new Random();
        this.customersForTheDay = new ArrayList<>();
        this.day = day;
        this.discountPricing = new MidWeekPricing();
        this.regularPricing = new RegularPricing();
        this.calculator = new PriceCalculator(this.regularPricing);

    }

    public List<Customer> selectCustomer(){
        customersForTheDay.clear(); //resets customers for a new day

        if(this.day == 1){
            //so that every player will see this customer on day 1
            customersForTheDay.add(allCustomers.get(0));

        }else {
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


    public void greetCustomers() {
        System.out.println("\nCustomer Arriving:");
        for (Customer customer : customersForTheDay) {
            System.out.println(customer.getPersonality().greetingDialogue(customer));
        }
    }

    public void receiveRatings() {
        System.out.println("\nCustomer Ratings:");
        for (Customer customer : customersForTheDay) {
            System.out.println(customer.getPersonality().ratingDialogue(customer));
        }
    }

    public void customersLeave() {
        System.out.println("\nCustomers Leaving:");
        for (Customer customer : customersForTheDay) {
            System.out.println(customer.getPersonality().leavingDialogue(customer));
        }
    }

    // Calls the next customer
    public Customer getNextCustomer() {
        if (!customersForTheDay.isEmpty()) {
            return customersForTheDay.remove(0); // removes and returns the first customer
        }
        return null; // no more customers
    }

    public double calculatePrice(double basePrice){
        int[] midWeekDays = {2,3,4};
        for (int day : midWeekDays) {
            if (this.day == day) {
                this.calculator.setStrategy(this.discountPricing);
                return this.calculator.calculatePrice(basePrice);
            }
        }
        this.calculator.setStrategy(this.regularPricing);
        return this.calculator.calculatePrice(basePrice);


    }


    // this is how i imagine the game class goes -> leaving this here as thoughts until game class created i guess ;)
//    public void start() {
    //setup of game stuff
//        dayCreator.selectCustomers(currentDay);

//        // Game loop where to call different dialogues
//        Customer currentCustomer;

//        while ((currentCustomer = dayCreator.getNextCustomer()) != null) {
//            dayCreator.greetCustomer(currentCustomer);
    //include other stuff here like the making of the pizza

//            dayCreator.receiveRating(currentCustomer);
//after the rating have the pricing stuff i guess
//             dayCreator.customerLeaves(currentCustomer);
//        }
//        // end of the day so it moves to the next day
//        currentDay++;
//    }
}
