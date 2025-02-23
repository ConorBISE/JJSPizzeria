package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.framework.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconButton extends Component {
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private EventHandler<ActionEvent> onAction;

    public EventHandler<ActionEvent> getOnAction() {
        return onAction;
    }

    public void setOnAction(EventHandler<ActionEvent> onAction) {
        this.onAction = onAction;
    }

    @Override
    protected Node getRoot() {
        String resourcePath = "/org/jjspizzeria/jjspizzeria/images/" + icon + ".png";

        Image image = new Image(getClass().getResourceAsStream(resourcePath));
        ImageView icon = new ImageView(image);

        icon.setPreserveRatio(true);
        icon.setFitWidth(65);

        Button button = new Button("", icon);
        button.getStyleClass().add("topping-button");
        button.setOnAction(onAction);

        return button;
    }
    
}
