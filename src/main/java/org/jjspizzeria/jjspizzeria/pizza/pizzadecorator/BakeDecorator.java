package org.jjspizzeria.jjspizzeria.pizza.pizzadecorator;

public class BakeDecorator extends PizzaDecorator {
    private final String bakeType;
    public BakeDecorator(Pizza pizza, String bakeType) {
        super(pizza);
        this.bakeType = bakeType;
    }

    public BakeDecorator(String bakeType) {
        this.bakeType = bakeType;
    }

    public String getDescription() {
        return pizza.getDescription() + " " + bakeType + " baked";
    }

    public String getBakeType() {
        return bakeType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BakeDecorator))
            return false;

        BakeDecorator other = (BakeDecorator) obj;
        return getBakeType().equals(other.getBakeType());
    }
}
