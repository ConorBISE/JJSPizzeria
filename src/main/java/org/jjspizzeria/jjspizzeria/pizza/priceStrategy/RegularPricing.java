package org.jjspizzeria.jjspizzeria.pizza.pricestrategy;

public class RegularPricing implements PricingStrategy{
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}
