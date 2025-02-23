package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.command.PizzaCommandInvoker;
import org.jjspizzeria.jjspizzeria.framework.Component;

import javafx.scene.Node;

public class PizzaCommandBox extends Component {

    @Override

    protected Node getRoot() {
        // TODO: we shouldn't be making a PizzaCommandOpener here!
        return new PizzaCommandInvoker().getHBox();
    }
    
}
