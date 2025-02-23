module org.jjspizzeria.jjspizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens org.jjspizzeria.jjspizzeria to javafx.fxml;
    opens org.jjspizzeria.jjspizzeria.components to javafx.fxml;
    opens org.jjspizzeria.jjspizzeria.screens to javafx.fxml;

    exports org.jjspizzeria.jjspizzeria;
}