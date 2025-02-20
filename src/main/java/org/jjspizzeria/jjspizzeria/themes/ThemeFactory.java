package org.jjspizzeria.jjspizzeria.themes;

import javafx.scene.image.ImageView;

public interface ThemeFactory {
    ImageView createBanner();
    String getThemeName();
    String getBackgroundCSSClass();
}
