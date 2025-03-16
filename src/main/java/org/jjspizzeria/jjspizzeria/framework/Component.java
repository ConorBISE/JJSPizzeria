package org.jjspizzeria.jjspizzeria.framework;

import org.jjspizzeria.jjspizzeria.framework.Component;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

public abstract class Component extends Group {
    private boolean injectedRoot = false;

    protected Component() {}
    
    @Override
    public ObservableList<Node> getChildren() {
        ObservableList<Node> children = super.getChildren();
        
        if (!injectedRoot) {
            children.add(getRoot());
            injectedRoot = true;
        }

        return children;
    }

    protected abstract Node getRoot();
}
