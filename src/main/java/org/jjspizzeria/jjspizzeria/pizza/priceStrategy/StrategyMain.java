package org.jjspizzeria.jjspizzeria.pizza.priceStrategy;

public class StrategyMain {

    public static void main(String[] args) {
        double productPrice = 100.0;

        //Regular Pricing
        PricingStrategy regularPricing = new RegularPricing();
        PriceCalculator priceCalculator = new PriceCalculator(regularPricing);
        System.out.println("Regular customer price: " +  priceCalculator.calculatePrice(productPrice));


        //Discount Pricing
        PricingStrategy discountPricing = new MidWeekPricing();
        priceCalculator.setStrategy(discountPricing);
        System.out.println("Discount customer price: " + priceCalculator.calculatePrice(productPrice));
    }

}
