package org.jjspizzeria.jjspizzeria.components;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.jjspizzeria.jjspizzeria.framework.Component;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.PizzaState;
import org.jjspizzeria.jjspizzeria.pizza.observer.PizzaObserver;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.*;

import java.util.ArrayList;
import java.util.List;

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
     * Called whenever the PizzaManager notifies its observers.
     * We rebuild the visual representation by stacking the images.
     *
     * @param pizza The top of the decorator chain.
     * @param state The current state of the pizza.
     */
    @Override
    public void onPizzaChanged(Pizza pizza, PizzaState state) {
        rootPane.getChildren().clear();

        // Traverse the decorator chain and collect all decorators.
        List<PizzaDecorator> decorators = new ArrayList<>();
        Pizza current = pizza;
        while (current instanceof PizzaDecorator) {
            PizzaDecorator decorator = (PizzaDecorator) current;
            decorators.add(decorator);
            current = decorator.getPizza();
        }
        // 'current' now is the base pizza.

        // Determine base image based on the pizza state and toppings.
        String baseImageName;
        if (state == PizzaState.UNBAKED) {
            baseImageName = "raw-pizza.png";
        } else {
            if (hasCheeseTopping(decorators)) {
                baseImageName = "baked-cheese-pizza.png";
            } else {
                baseImageName = "baked-pizza.png";
            }
        }

        ImageView baseImageView = new ImageView(loadImage(baseImageName));
        baseImageView.setPreserveRatio(true);
        baseImageView.setFitWidth(240);
        rootPane.getChildren().add(baseImageView);

        // Iterate over the decorators in order from innermost to outermost.
        // This preserves the stacking order (base at bottom, outer layers on top).
        for (int i = decorators.size() - 1; i >= 0; i--) {
            PizzaDecorator decorator = decorators.get(i);

            // Process topping decorators.
            if (decorator instanceof ToppingDecorator toppingDecorator) {
                // If pizza is baked, skip drawing raw cheese overlay.
                if (state != PizzaState.UNBAKED &&
                        "cheese".equalsIgnoreCase(toppingDecorator.getTopping().getName())) {
                    continue;
                }
                String toppingImagePath = toppingDecorator.getPath();
                ImageView toppingImageView = new ImageView(loadImage(toppingImagePath));
                toppingImageView.setPreserveRatio(true);
                toppingImageView.setFitWidth(240);
                rootPane.getChildren().add(toppingImageView);
            }
            // Process slice decorators.
            else if (decorator instanceof SliceDecorator sliceDecorator) {
                int slices = sliceDecorator.getSlices();
                String imagePath = slices + "slice.png"; // e.g. "4slice.png"
                ImageView sliceImageView = new ImageView(loadImage(imagePath));
                sliceImageView.setPreserveRatio(true);
                sliceImageView.setFitWidth(240);
                rootPane.getChildren().add(sliceImageView);
            }
            // Process box decorators.
            else if (decorator instanceof BoxDecorator) {
                String imagePath = "pizza-box.png";
                ImageView boxImageView = new ImageView(loadImage(imagePath));
                boxImageView.setPreserveRatio(true);
                boxImageView.setFitWidth(240);
                rootPane.getChildren().add(boxImageView);
            }
        }
    }

    private Image loadImage(String imageName) {
        String path = "/org/jjspizzeria/jjspizzeria/images/" + imageName;
        return new Image(getClass().getResourceAsStream(path));
    }

    /**
     * Checks if any of the decorators in the chain is a cheese topping.
     *
     * @param decorators The list of decorators from the chain.
     * @return true if a cheese topping is present; false otherwise.
     */
    private boolean hasCheeseTopping(List<PizzaDecorator> decorators) {
        for (PizzaDecorator decorator : decorators) {
            if (decorator instanceof ToppingDecorator toppingDecorator &&
                    "cheese".equalsIgnoreCase(toppingDecorator.getTopping().getName())) {
                return true;
            }
        }
        return false;
    }
}
