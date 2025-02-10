package org.jjspizzeria.jjspizzeria.themes.factories;

import org.jjspizzeria.jjspizzeria.themes.components.Button.Buttons;
import org.jjspizzeria.jjspizzeria.themes.components.Button.FireButtons;

public class FireModeFactory implements ThemeFactory{
    @Override
    public Buttons createButtons() {
        return new FireButtons();
    }
}
