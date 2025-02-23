package org.jjspizzeria.jjspizzeria.framework;

import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.scene.Node;

public abstract class Screen extends FXMLComponent {

    protected Screen(String fxmlPath) {
        super(fxmlPath);
    }

    @Override
    protected Node getRoot() {
        Node root = super.getRoot();

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
