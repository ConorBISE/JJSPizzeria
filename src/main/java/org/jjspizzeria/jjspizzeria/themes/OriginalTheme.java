package org.jjspizzeria.jjspizzeria.themes;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OriginalTheme implements ThemeFactory{
    @Override
    public String getBackgroundCSSClass() {
        return "original-theme";
    }

    @Override
    public ImageView createBanner() {
        Image bannerImage = new Image(getClass().getResourceAsStream("/org/jjspizzeria/jjspizzeria/images/banner.png"));
        ImageView bannerView = new ImageView(bannerImage);
        return bannerView;
    }

    @Override
    public String getThemeName() {
        return "Original";
    }

}