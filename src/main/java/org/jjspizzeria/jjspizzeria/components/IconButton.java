package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.framework.Component;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconButton extends Component {
    private String imageFilename;

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    @Override
    protected Node getRoot() {
        String resourcePath = "/org/jjspizzeria/jjspizzeria/images/" + imageFilename;

        Image image = new Image(getClass().getResourceAsStream(resourcePath));
        ImageView icon = new ImageView(image);

        icon.setPreserveRatio(true);
        icon.setFitWidth(65);

        Button button = new Button("", icon);
        button.getStyleClass().add("topping-button");

        return button;
    }
    
}
