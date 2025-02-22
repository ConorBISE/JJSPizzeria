package org.jjspizzeria.jjspizzeria.framework;

import java.io.IOException;

import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class FXMLView extends View {

    private String fxmlPath;

    protected FXMLView(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    @Override
    protected Node getRoot() {
        Node root;

        try {
            root = FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            System.out.println("Error loading FXML " + fxmlPath);
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
}
