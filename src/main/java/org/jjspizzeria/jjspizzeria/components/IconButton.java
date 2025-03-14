package org.jjspizzeria.jjspizzeria.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jjspizzeria.jjspizzeria.pizza.PizzaState;

public class IconButton extends ObserverButton {
    private String iconName;

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
