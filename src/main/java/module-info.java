module org.jjspizzeria.jjspizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens org.jjspizzeria.jjspizzeria to javafx.fxml;
    opens org.jjspizzeria.jjspizzeria.views to javafx.fxml;

    exports org.jjspizzeria.jjspizzeria;
}