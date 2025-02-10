package org.jjspizzeria.jjspizzeria.themes.factories;

import org.jjspizzeria.jjspizzeria.themes.components.Button.Buttons;
import org.jjspizzeria.jjspizzeria.themes.components.Button.LightButtons;
import org.jjspizzeria.jjspizzeria.themes.components.Window.LightModeWindow;
import org.jjspizzeria.jjspizzeria.themes.components.Window.Windows;

public class LightModeFactory implements ThemeFactory{


//    @Override
//    public Windows createWindow() {
//        return new LightModeWindow();
//    }

    @Override
    public Buttons createButtons() {
        return new LightButtons();
    }
}
