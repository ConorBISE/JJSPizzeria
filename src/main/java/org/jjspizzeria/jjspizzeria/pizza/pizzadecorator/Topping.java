package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class Topping {
    /**
     Topping entity, contains information about each topping
     Name: name of topping
     Cost: cost of adding topping to pizza
     Path: path to the image of topping
     */


    private String name;
    private double cost;
    private String path;

    //Constructor
    public Topping(String name, double cost, String path) {
        this.name = name;
        this.cost = cost;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Topping other = (Topping) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

    
}
