package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.MidWeekPricing;
import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.PriceCalculator;
import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.PricingStrategy;
import org.jjspizzeria.jjspizzeria.pizza.priceStrategy.RegularPricing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingStrategyTest {
    @Test
    public void testRegularPricingStrategy() {
        double productPrice = 100.0;

        PricingStrategy regularPricing = new RegularPricing();
        PriceCalculator priceCalculator = new PriceCalculator(regularPricing);
        double calculatedPrice = priceCalculator.calculatePrice(productPrice);
        assertEquals(productPrice, calculatedPrice);
    }

    @Test
    public void testMidWeekPricingStrategy(){
        double productPrice = 100.0;
        double expectedPrice = 80.0;

        PricingStrategy midWeekPricing = new MidWeekPricing();
        PriceCalculator priceCalculator = new PriceCalculator(midWeekPricing);
        double calculatedPrice = priceCalculator.calculatePrice(productPrice);

        assertEquals(expectedPrice, calculatedPrice);
    }

    @Test
    public void testSettingStrategies(){
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
