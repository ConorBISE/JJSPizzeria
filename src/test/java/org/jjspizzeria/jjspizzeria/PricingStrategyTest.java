package org.jjspizzeria.jjspizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.MidWeekPricing;
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.PriceCalculator;
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.PricingStrategy;
import org.jjspizzeria.jjspizzeria.pizza.pricestrategy.RegularPricing;

public class PricingStrategyTest {
    @Test
    void testRegularPricingStrategy() {
        double productPrice = 100.0;

        PricingStrategy regularPricing = new RegularPricing();
        PriceCalculator priceCalculator = new PriceCalculator(regularPricing);
        double calculatedPrice = priceCalculator.calculatePrice(productPrice);
        assertEquals(productPrice, calculatedPrice);
    }

    @Test
    void testMidWeekPricingStrategy(){
        double productPrice = 100.0;
        double expectedPrice = 80.0;

        PricingStrategy midWeekPricing = new MidWeekPricing();
        PriceCalculator priceCalculator = new PriceCalculator(midWeekPricing);
        double calculatedPrice = priceCalculator.calculatePrice(productPrice);

        assertEquals(expectedPrice, calculatedPrice);
    }

    @Test
    void testSettingStrategies(){
        double productPrice = 100.0;

        PricingStrategy regularPricing = new RegularPricing();
        PriceCalculator priceCalculator = new PriceCalculator(regularPricing);
        double calculatedPrice = priceCalculator.calculatePrice(productPrice);
        assertEquals(productPrice, calculatedPrice);


        PricingStrategy midWeekPricing = new MidWeekPricing();
        priceCalculator.setStrategy(midWeekPricing);
        double newCalculatedPrice = priceCalculator.calculatePrice(productPrice);

        double expectedPrice = 80.0;
        assertEquals(expectedPrice, newCalculatedPrice);
    }
}
