package org.jjspizzeria.jjspizzeria.components;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.jjspizzeria.jjspizzeria.framework.Component;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.PizzaState;
import org.jjspizzeria.jjspizzeria.pizza.observer.PizzaObserver;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.ToppingDecorator;

public class PizzaView extends Component implements PizzaObserver {

    private final StackPane rootPane;

    public PizzaView() {
        // Stack pane to hold all the images.
        rootPane = new StackPane();
        // Register as an observer of PizzaManager.
        PizzaManager.getInstance().addObserver(this);

        // Show the base pizza image when the game loads.
        ImageView baseImageView = new ImageView(loadImage("raw-pizza.png"));
        baseImageView.setPreserveRatio(true);
        baseImageView.setFitWidth(240);
        rootPane.getChildren().add(baseImageView);
    }

    @Override
    protected Node getRoot() {
        return rootPane;
    }

    /**
     * This method is called whenever the PizzaManager notifies its observers.
     * It updates the visual representation by stacking the appropriate base
     * and topping images.
     *
     * @param pizza The top of the decorator chain for the current pizza.
     * @param state The current state of the pizza.
     */
    @Override
    public void onPizzaChanged(Pizza pizza, PizzaState state) {
        rootPane.getChildren().clear();

        // Determine which base image to use based on state and toppings.
        String baseImageName;
        if (state == PizzaState.UNBAKED) {
            baseImageName = "raw-pizza.png";
        } else {
            if (hasCheeseTopping(pizza)) {
                baseImageName = "baked-cheese-pizza.png";
            } else {
                baseImageName = "baked-pizza.png";
            }
        }

        ImageView baseImageView = new ImageView(loadImage(baseImageName));
        baseImageView.setPreserveRatio(true);
        baseImageView.setFitWidth(240);
        rootPane.getChildren().add(baseImageView);

        // Iterate over the topping decorators and stack their images.
        Pizza current = pizza;
        while (current instanceof ToppingDecorator toppingDecorator) {
            // EDGE CASE: If the topping is cheese and the pizza is baked, skip overlaying the raw cheese image.
            if (state != PizzaState.UNBAKED &&
                    "cheese".equalsIgnoreCase(toppingDecorator.getTopping().getName())) {
                current = toppingDecorator.getPizza();
                continue;
            }

            String toppingImagePath = toppingDecorator.getPath();

            ImageView toppingImageView = new ImageView(loadImage(toppingImagePath));
            toppingImageView.setPreserveRatio(true);
            toppingImageView.setFitWidth(240);

            rootPane.getChildren().add(toppingImageView);
            current = toppingDecorator.getPizza();
        }

    }

    private Image loadImage(String imageName) {
        String path = "/org/jjspizzeria/jjspizzeria/images/" + imageName;
        return new Image(getClass().getResourceAsStream(path));
    }

    private boolean hasCheeseTopping(Pizza pizza) {
        Pizza current = pizza;
        while (current instanceof ToppingDecorator toppingDecorator) {
            if ("cheese".equalsIgnoreCase(toppingDecorator.getTopping().getName())) {
                return true;
            }
            current = toppingDecorator.getPizza();
        }
        return false;
    }
}
