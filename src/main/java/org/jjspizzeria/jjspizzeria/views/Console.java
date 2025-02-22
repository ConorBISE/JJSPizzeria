package org.jjspizzeria.jjspizzeria.views;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.framework.View;

import javafx.scene.Node;

public class Console extends View {

    @Override
    protected Node getRoot() {
        return GameConsole.getInstance().getTextArea();
    }
    
}
