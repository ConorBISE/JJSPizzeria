package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.pizza.CheeseCrustPizza;
import org.jjspizzeria.jjspizzeria.pizza.ConcreteTopping;
import org.jjspizzeria.jjspizzeria.pizza.Pizza;
import org.jjspizzeria.jjspizzeria.pizza.ToppingEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    @Test
    public void testBasePizzaCost() {
        Pizza pizza = new CheeseCrustPizza();
        assertEquals(9.0, pizza.getCost());
    }

    @Test
    public void testBasePizzaDescription() {
        Pizza pizza = new CheeseCrustPizza();
        assertEquals("Cheese Crust Pizza", pizza.getDescription());
    }

    @Test
    public void testPizzaWithCheese() {
        ToppingEntity cheese = new ToppingEntity("Cheese", 1.00, "cheese.png");
        Pizza pizza = new ConcreteTopping(new CheeseCrustPizza(), cheese);
        
        assertEquals(10.0, pizza.getCost());
        assertEquals("Cheese Crust Pizza + Cheese", pizza.getDescription());
    }

    @Test
    public void testPizzaWithMultipleToppings() {
        ToppingEntity cheese = new ToppingEntity("Cheese", 1.00, "cheese.png");
        ToppingEntity jalapeno = new ToppingEntity("Jalapeno", 1.50, "jalapeno.png");
        
        Pizza pizza = new CheeseCrustPizza();
        pizza = new ConcreteTopping(pizza, cheese);
        pizza = new ConcreteTopping(pizza, jalapeno);
        
        assertEquals(11.50, pizza.getCost());
        assertEquals("Cheese Crust Pizza + Cheese + Jalapeno", pizza.getDescription());
    }



    @Test
    public void testToppingEntityGetters() {
        ToppingEntity topping = new ToppingEntity("Ham", 2.00, "ham.png");
        
        assertEquals("Ham", topping.getName());
        assertEquals(2.00, topping.getCost());
        assertEquals("ham.png", topping.getPath());
    }
}