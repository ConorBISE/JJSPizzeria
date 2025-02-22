package org.jjspizzeria.jjspizzeria.views;

import java.io.IOException;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.framework.FXMLView;

public class GameView extends FXMLView {

    public GameView() throws IOException {
        super("/org/jjspizzeria/jjspizzeria/layouts/game.fxml");
        GameConsole.getInstance().append("Welcome to JJ's pizzeria!");
    }

}
