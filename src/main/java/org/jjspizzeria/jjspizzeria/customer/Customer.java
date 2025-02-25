package org.jjspizzeria.jjspizzeria.customer;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Customer {
    private String name;
    private Order order;
    private Personality personality;

    public Customer(String name, Order order, Personality personality) {
        this.name = name;
        this.order = order;
        this.personality = personality;
    }

    public String getName() {return name;}
    public Order getOrder() {return order;}
    public Personality getPersonality(){return personality;}
    public static List<Customer> loadCustomers(String resourcePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = Customer.class.getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        // reads JSON content into JsonNode
        JsonNode root = objectMapper.readTree(inputStream);
        List<Customer> customers = new ArrayList<>();

        for (JsonNode node : root) {
            String name = node.get("name").asText();

            //extracting order details
            List<String> toppings = objectMapper.convertValue(node.get("order").get("toppings"), List.class);
            int slices = node.get("order").get("slices").asInt();
            String bakingPreference = node.get("order").get("bakingPreference").asText();

            //creating an order object with the data we just got
            Order order = new Order(toppings, slices, bakingPreference);

            String type = node.get("type").asText();
            Personality personality = assignPersonality(type);

            customers.add(new Customer(name, order, personality));
        }
        return customers;
    }

    private static Personality assignPersonality(String type){
        switch(type.toUpperCase()){
            case "HAPPY":
               return new HappyDialogue();
            case "NEUTRAL":
               return new NeutralDialogue();
            case "ANGRY":
               return new AngryDialogue();
            default:
                throw new IllegalStateException("Unexpected value: " + type.toUpperCase());
        }
    }
}
