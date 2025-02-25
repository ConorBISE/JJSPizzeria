module org.jjspizzeria.jjspizzeria {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;
    requires javafx.base;

    requires com.fasterxml.jackson.databind;

    opens org.jjspizzeria.jjspizzeria to javafx.fxml;
    opens org.jjspizzeria.jjspizzeria.components to javafx.fxml;
    opens org.jjspizzeria.jjspizzeria.screens to javafx.fxml;

    exports org.jjspizzeria.jjspizzeria;
}