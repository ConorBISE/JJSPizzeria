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

    @Override
    public String getDescription() {
        return pizza.getDescription() + " " + bakeType + " baked";
    }

    public String getBakeType() {
        return bakeType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bakeType == null) ? 0 : bakeType.hashCode());
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

        BakeDecorator other = (BakeDecorator) obj;
        if (bakeType == null) {
            if (other.bakeType != null) {
                return false;
            }
        } else if (!bakeType.equals(other.bakeType)) {
            return false;
        }

        return true;
    }

}
