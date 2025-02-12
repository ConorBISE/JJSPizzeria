package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.BasePizza;
import org.jjspizzeria.jjspizzeria.pizza.CheeseDecorator;
import org.jjspizzeria.jjspizzeria.pizza.Pizza;
import org.jjspizzeria.jjspizzeria.pizza.Topping;
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
        assertEquals("Cheese Crust Pizza", pizza.getDescription());
    }

    @Test
    public void testPizzaWithCheese() {
        Topping cheese = new Topping("Cheese", 1.00, "cheese.png");
        Pizza pizza = new CheeseDecorator(new BasePizza(), cheese);
        
        assertEquals(10.0, pizza.getCost());
        assertEquals("Cheese Crust Pizza + Cheese", pizza.getDescription());
    }

    @Test
    public void testPizzaWithMultipleToppings() {
        Topping cheese = new Topping("Cheese", 1.00, "cheese.png");
        Topping jalapeno = new Topping("Jalapeno", 1.50, "jalapeno.png");
        
        Pizza pizza = new BasePizza();
        pizza = new CheeseDecorator(pizza, cheese);
        pizza = new CheeseDecorator(pizza, jalapeno);
        
        assertEquals(11.50, pizza.getCost());
        assertEquals("Cheese Crust Pizza + Cheese + Jalapeno", pizza.getDescription());
    }



    @Test
    public void testToppingEntityGetters() {
        Topping topping = new Topping("Ham", 2.00, "ham.png");
        
        assertEquals("Ham", topping.getName());
        assertEquals(2.00, topping.getCost());
        assertEquals("ham.png", topping.getPath());
    }
}