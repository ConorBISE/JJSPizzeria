package org.jjspizzeria.jjspizzeria.screens;

import org.jjspizzeria.jjspizzeria.framework.Screen;

import javafx.fxml.FXML;

public class WelcomeScreen extends Screen {

    public WelcomeScreen() {
        super("/org/jjspizzeria/jjspizzeria/layouts/welcome.fxml");
    }

    @FXML
    void initialize() {
        // For most events, we would register the appropriate handler
        // in our view file. In order to handle window-wide keypresses, though
        // - we need to add an event handler on our root scene
        getScene().setOnKeyPressed(e -> {
            navigate(new GameScreen());
        });
    }
}
