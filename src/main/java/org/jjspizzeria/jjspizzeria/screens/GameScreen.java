package org.jjspizzeria.jjspizzeria.screens;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.customer.Customer;
import org.jjspizzeria.jjspizzeria.customer.DayCreator;
import org.jjspizzeria.jjspizzeria.framework.Screen;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.BasePizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;

import java.io.IOException;
import java.util.List;

public class GameScreen extends Screen {
    private int currentDay = 1;
    private DayCreator dayCreator;

    public GameScreen() throws IOException {
        super("/org/jjspizzeria/jjspizzeria/layouts/game.fxml");
        GameConsole.getInstance().append("Welcome to JJ's pizzeria!");
        this.dayCreator = new DayCreator(currentDay);
        startGameLoop();
    }

    private void startGameLoop() {
        while (true) {
            GameConsole.getInstance().append("Day " + currentDay + " begins!");


            List<Customer> customers = dayCreator.selectCustomer();
            System.out.println("Customers selected for the day: " + customers.size());

            Customer customer;

            while((customer = dayCreator.getNextCustomer()) != null ){

                GameConsole.getInstance().append(dayCreator.greetCustomers(customer));
                PizzaManager.getInstance().reset();
                GameConsole.getInstance().append("[Displaying Base Pizza] " + PizzaManager.getInstance().getPizza().getPath());


                GameConsole.getInstance().append(dayCreator.receiveRatings(customer));
                GameConsole.getInstance().append(dayCreator.customersLeave(customer));
            }

                GameConsole.getInstance().append("Day " + currentDay + " is over!");
                currentDay++;
                dayCreator.setDay(currentDay);

                if (currentDay > 3) {
                    GameConsole.getInstance().append("Game Over! Thanks for playing.");
                    break;
                }
            }
        }
    }
