package org.jjspizzeria.jjspizzeria.framework;

import org.jjspizzeria.jjspizzeria.themes.ThemeManager;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

public abstract class Screen extends FXMLComponent {
    protected Screen(String fxmlPath) {
        super(fxmlPath);
    }

    @Override
    protected Node getRoot() {
        Node root = super.getRoot();

        root.getStyleClass()
            .add(
                ThemeManager
                    .getInstance()
                    .getCurrentTheme()
                    .getBackgroundCSSClass()
            );

        return root;
    }

    protected void onNavigateAway() {}

    public static final EventType<Event> RESIZE_WINDOW = new EventType<>("JJ_RESIZE_WINDOW");

    protected void navigate(Screen screen) {
        onNavigateAway();
        getScene().setRoot(screen);
        screen.fireEvent(new Event(RESIZE_WINDOW));
    }
}
