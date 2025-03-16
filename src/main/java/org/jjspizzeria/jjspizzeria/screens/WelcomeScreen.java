package org.jjspizzeria.jjspizzeria.screens;

import org.jjspizzeria.jjspizzeria.framework.Screen;
import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.fxml.FXML;

public class WelcomeScreen extends Screen {
    public WelcomeScreen() {
        super("/org/jjspizzeria/jjspizzeria/layouts/welcome.fxml");
    }

    @FXML
    private void initialize() {
        // For most events, we would register the appropriate handler
        // in our view file. In order to handle window-wide keypresses, though
        // - we need to add an event handler on our root scene
        getScene().setOnKeyPressed(e -> {
            navigate(new GameScreen());
        });
    }


    @Override
    protected void onNavigateAway() {
        // As a consequence of us setting a handler on the root scene:
        // JavaFX doesn't automatically remove it when we navigate away
        // We'll have to do that ourselves as well.
        getScene().setOnKeyPressed(null);
    }

    @FXML
    private void setStandardTheme() {
        ThemeManager.getInstance().setThemeByButton("Original");
        navigate(new GameScreen());
    }

    @FXML
    private void setEasterTheme() {
        ThemeManager.getInstance().setThemeByButton("Easter");
        navigate(new GameScreen());
    }
}
