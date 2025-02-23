package org.jjspizzeria.jjspizzeria.framework;

import java.io.IOException;

import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class Screen extends Component {

    private String fxmlPath;

    protected Screen(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    @Override
    protected Node getRoot() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setController(this);
        
        Node root;
        try {
            root = loader.load(); 
        } catch (IOException e) {
            System.out.println("Error loading FXML " + fxmlPath);
            e.printStackTrace();
            return null;
        }

        root.getStyleClass()
            .add(
                ThemeManager
                    .getInstance()
                    .getCurrentTheme()
                    .getBackgroundCSSClass()
            );

        return root;
    }

    protected void navigate(Screen screen) {
        getScene().setRoot(screen);
    }
}
