package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.GameConsole;
import org.jjspizzeria.jjspizzeria.framework.Component;

import javafx.scene.Node;

public class Console extends Component {

    @Override
    protected Node getRoot() {
        return GameConsole.getInstance().getTextArea();
    }
    
}
