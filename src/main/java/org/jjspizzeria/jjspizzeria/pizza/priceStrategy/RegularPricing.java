package org.jjspizzeria.jjspizzeria.pizza.priceStrategy;

public class RegularPricing implements PricingStrategy{
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}
