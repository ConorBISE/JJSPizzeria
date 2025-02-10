package org.jjspizzeria.jjspizzeria.themes.factories;

import org.jjspizzeria.jjspizzeria.themes.components.Button.Buttons;
import org.jjspizzeria.jjspizzeria.themes.components.Button.DarkButtons;

public class DarkModeFactory implements ThemeFactory{
    @Override
    public Buttons createButtons() {
        return new DarkButtons();
    }
}
