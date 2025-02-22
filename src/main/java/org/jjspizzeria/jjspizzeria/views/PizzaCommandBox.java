package org.jjspizzeria.jjspizzeria.views;

import org.jjspizzeria.jjspizzeria.command.PizzaCommandInvoker;
import org.jjspizzeria.jjspizzeria.framework.View;

import javafx.scene.Node;

public class PizzaCommandBox extends View {

    @Override

    protected Node getRoot() {
        // TODO: we shouldn't be making a PizzaCommandOpener here!
        return new PizzaCommandInvoker().getHBox();
    }
    
}
