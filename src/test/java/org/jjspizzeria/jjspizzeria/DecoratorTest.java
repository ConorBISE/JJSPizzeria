package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    public void testBasePizzaCost() {
        Pizza pizza = new BasePizza();
        assertEquals(9.0, pizza.getCost());
    }

    @Test
    public void testBasePizzaDescription() {
        Pizza pizza = new BasePizza();
        assertEquals("Pizza", pizza.getDescription());
    }

    @Test
    public void testPizzaWithCheese() {
        Pizza pizza = new CheeseDecorator(new BasePizza());
        
        assertEquals(10.50, pizza.getCost());
        assertEquals("Pizza + Cheese", pizza.getDescription());
    }

    @Test
    public void testPizzaWithMultipleToppings() {
        Pizza pizza = new BasePizza();
        pizza = new CheeseDecorator(pizza);
        pizza = new PepperoniDecorator(pizza);
        
        assertEquals(12.50, pizza.getCost());
        assertEquals("Pizza + Cheese + Pepperoni", pizza.getDescription());
    }



    @Test
    public void testToppingEntityGetters() {
        Topping topping = new Topping("Ham", 2.00, "ham.png");
        
        assertEquals("Ham", topping.getName());
        assertEquals(2.00, topping.getCost());
        assertEquals("ham.png", topping.getPath());
    }
}