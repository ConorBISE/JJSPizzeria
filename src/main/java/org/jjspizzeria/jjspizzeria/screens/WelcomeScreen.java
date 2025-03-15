package org.jjspizzeria.jjspizzeria.screens;

import org.jjspizzeria.jjspizzeria.framework.Screen;
import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.fxml.FXML;

public class WelcomeScreen extends Screen {
    public WelcomeScreen() {
        super("/org/jjspizzeria/jjspizzeria/layouts/welcome.fxml");
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
