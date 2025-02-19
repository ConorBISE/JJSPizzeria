package org.jjspizzeria.jjspizzeria.themes;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EasterTheme implements ThemeFactory{

    @Override
    public String getBackgroundCSSClass() {
        return "easter-theme";
    }

    @Override
    public ImageView createBanner() {
        Image bannerImage = new Image(getClass().getResourceAsStream("/org/jjspizzeria/jjspizzeria/images/easter-banner.png"));
        ImageView bannerView = new ImageView(bannerImage);
        bannerView.setFitWidth(400);
        bannerView.setPreserveRatio(true);
        bannerView.setTranslateY(-230);
        return bannerView;
    }

    @Override
    public String getThemeName() {
        return "Easter";
    }
}
