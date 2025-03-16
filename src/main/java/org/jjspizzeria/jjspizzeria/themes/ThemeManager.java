package org.jjspizzeria.jjspizzeria.themes;

import java.util.ArrayList;
import java.util.List;

public class ThemeManager {
    private static ThemeManager instance;
    private ThemeFactory currentTheme;
    private List<ThemeFactory> availableThemes;

    private ThemeManager() {
        availableThemes = new ArrayList<>();
        availableThemes.add(new OriginalTheme());
        availableThemes.add(new EasterTheme());
        currentTheme = availableThemes.get(0); // default set to Original theme
    }

    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public ThemeFactory getCurrentTheme() {
        return currentTheme;
    }

    // this method if there are 2 buttons to choose theme on the welcome page(before
    // console)
    public void setThemeByButton(String themeName) { // using names to be easier to identify themes
        for (ThemeFactory theme : availableThemes) {
            if (theme.getThemeName().equalsIgnoreCase(themeName)) {
                currentTheme = theme;
                break;
            }
        }
    }
}
