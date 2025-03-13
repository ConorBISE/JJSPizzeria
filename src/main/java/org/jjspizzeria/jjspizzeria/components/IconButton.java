package org.jjspizzeria.jjspizzeria.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jjspizzeria.jjspizzeria.framework.Component;
import org.jjspizzeria.jjspizzeria.pizza.PizzaManager;
import org.jjspizzeria.jjspizzeria.pizza.PizzaState;
import org.jjspizzeria.jjspizzeria.pizza.observer.PizzaObserver;
import org.jjspizzeria.jjspizzeria.pizza.pizzadecorator.Pizza;

public class IconButton extends ObserverButton {
    private String iconName;
    private EventHandler<ActionEvent> onAction;
    private Button button;

    public IconButton() {
        super();
        getStyleClass().add("topping-button");
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
        updateIcon();
    }

    private void updateIcon() {
        if (iconName != null && !iconName.isEmpty()) {
            String resourcePath = "/org/jjspizzeria/jjspizzeria/images/" + iconName + ".png";
            Image image = new Image(getClass().getResourceAsStream(resourcePath));
            ImageView iconView = new ImageView(image);
            iconView.setPreserveRatio(true);
            iconView.setFitWidth(65);


            setGraphic(iconView);
        } else {
            setGraphic(null);
        }
    }

    @Override
    protected boolean shouldEnableForState(PizzaState state) {
        return state == PizzaState.UNBAKED;
    }
}
