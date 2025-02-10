package org.jjspizzeria.jjspizzeria.themes.components.Button;

import javafx.scene.control.Button;

public class DarkButtons implements Buttons{
    @Override
    public Button createButton() {
        Button button = new Button("click me");
        button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        return button;
    }
}
