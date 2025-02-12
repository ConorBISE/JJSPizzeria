public class Topping {
    public Topping (String name, double cost, String path) {
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
}
