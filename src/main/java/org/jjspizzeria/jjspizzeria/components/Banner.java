package org.jjspizzeria.jjspizzeria.components;

import org.jjspizzeria.jjspizzeria.framework.Component;
import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.scene.Node;

public class Banner extends Component {

    public Banner() {
        super();
    }

    protected Node getRoot() {
        return ThemeManager.getInstance().getCurrentTheme().createBanner();
    }

}
