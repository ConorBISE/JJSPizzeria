package org.jjspizzeria.jjspizzeria;

import javafx.scene.control.TextArea;

/**
 * A Singleton class that ensures there's only one instance of the console and provides a global access point to it.
 * Internally manages a TextArea object.
 */
public class GameConsole {

    private static GameConsole instance;
    private TextArea textArea;

    private GameConsole() {
        // Initialize TextArea and set styles
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.getStyleClass().add("console");
    
        textArea.setMinWidth(660);
        textArea.setMinHeight(340);

        textArea.setMaxWidth(660);
        textArea.setMaxHeight(340);
    }

    /**
     * Returns the sole instance of GameConsole.
     */
    public static synchronized GameConsole getInstance() {
        if (instance == null) {
            instance = new GameConsole();
        }
        return instance;
    }

    /**
     * Appends text to the console’s TextArea.
     */
    public void append(String text) {
        textArea.appendText(text + "\n");
        // Scroll to the bottom whenever new text is appended
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * Exposes the console’s TextArea, so it can be placed in the scene.
     */
    public TextArea getTextArea() {
        return textArea;
    }

    public static void setTestInstance(GameConsole testInstance) {
        instance = testInstance;
    }
}
