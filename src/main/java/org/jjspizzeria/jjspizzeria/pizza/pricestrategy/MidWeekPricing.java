package org.jjspizzeria.jjspizzeria.pizza.pricestrategy;

public class MidWeekPricing implements PricingStrategy{
    @Override
    public double calculatePrice(double price) {
        return price * 0.8;
    }
}
