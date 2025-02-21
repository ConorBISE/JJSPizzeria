module org.jjspizzeria.jjspizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens org.jjspizzeria.jjspizzeria to javafx.fxml;
    exports org.jjspizzeria.jjspizzeria;
}