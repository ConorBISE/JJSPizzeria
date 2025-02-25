package org.jjspizzeria.jjspizzeria.screens;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.framework.Screen;

public class GameScreen extends Screen {

    public GameScreen() {
        super("/org/jjspizzeria/jjspizzeria/layouts/game.fxml");
        GameConsole.getInstance().append("Welcome to JJ's pizzeria!");
    }

}
