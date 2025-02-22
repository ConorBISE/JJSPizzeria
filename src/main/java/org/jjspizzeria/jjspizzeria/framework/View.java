package org.jjspizzeria.jjspizzeria.framework;

import java.io.IOException;

import org.jjspizzeria.jjspizzeria.framework.View;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class View extends Group {
    public boolean injectedRoot = false;

    public View() {}
    
    @Override
    public ObservableList<Node> getChildren() {
        ObservableList<Node> children = super.getChildren();
        
        if (!injectedRoot) {
            children.add(getRoot());
            injectedRoot = true;
        }

        return children;
    }


    abstract protected Node getRoot();

    public void show(Stage stage) throws IOException {
        Scene scene = new Scene(this, 760, 760);
        
        scene.getStylesheets()
            .add(
                getClass()
                .getResource("/org/jjspizzeria/jjspizzeria/styles.css")
                .toExternalForm()
            );

        stage.setScene(scene);
        stage.show();
    }

}
