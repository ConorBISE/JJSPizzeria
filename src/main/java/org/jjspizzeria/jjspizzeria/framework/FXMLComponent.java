package org.jjspizzeria.jjspizzeria.framework;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class FXMLComponent extends Component {

    private String fxmlPath;

    protected FXMLComponent(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    @Override
    protected Node getRoot() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setController(this);
        
        try {
            return loader.load(); 
        } catch (IOException e) {
            System.out.println("Error loading FXML " + fxmlPath);
            e.printStackTrace();
            return null;
        }
    }
}
