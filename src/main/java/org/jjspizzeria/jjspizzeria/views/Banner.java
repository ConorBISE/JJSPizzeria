package org.jjspizzeria.jjspizzeria.views;

import org.jjspizzeria.jjspizzeria.framework.View;
import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.scene.Node;

public class Banner extends View {

    public Banner() {
        super();
    }

    protected Node getRoot() {
        return ThemeManager.getInstance().getCurrentTheme().createBanner();
    }

}
