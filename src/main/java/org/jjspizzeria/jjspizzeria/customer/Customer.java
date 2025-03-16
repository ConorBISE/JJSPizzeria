package org.jjspizzeria.jjspizzeria.customer;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer {
    private String name;
    private List<String> toppings;
    private int slices;
    private Personality personality;
    private String bakingPreference;

    public Customer(String name, List<String> toppings, int slices, String bakingPreference, Personality personality) {
        this.name = name;
        this.toppings = toppings;
        this.slices = slices;
        this.bakingPreference = bakingPreference;
        this.personality = personality;
    }

    public String getName() {return name;}
    public List<String> getToppings() { return toppings; }
    public int getSlices() { return slices; }
    public String getBakingPreference() { return bakingPreference; }
    public Personality getPersonality(){return personality;}

    // Returns a formatted string of the order details
    public String getOrderDetails() {
        return "a pizza with " + String.join(", ", toppings) +
                " cut into " + slices + " slices and baked " + bakingPreference;
    }

    public static List<Customer> loadCustomers(String resourcePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customers = new ArrayList<>();

        try (InputStream inputStream = Customer.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            // Read JSON content into JsonNode
            JsonNode root = objectMapper.readTree(inputStream);

            for (JsonNode node : root) {
                String name = node.get("name").asText();


                List<String> toppings = objectMapper.convertValue(node.get("order").get("toppings"),
                        new TypeReference<List<String>>() {});

                int slices = node.get("order").get("slices").asInt();
                String bakingPreference = node.get("order").get("bakingPreference").asText();

                String type = node.get("type").asText();
                Personality personality = assignPersonality(type);

                customers.add(new Customer(name, toppings, slices, bakingPreference, personality));
            }
        } catch (IOException e) {
            throw new IOException("Error loading customers from " + resourcePath, e);
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
