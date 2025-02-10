package org.jjspizzeria.jjspizzeria.themes.components.Button;

import javafx.scene.control.Button;

public class FireButtons implements Buttons{
    @Override
    public Button createButton() {
        Button button = new Button("click me");
        button.setStyle("-fx-background-color: orange;");
        return button;
    }
}
