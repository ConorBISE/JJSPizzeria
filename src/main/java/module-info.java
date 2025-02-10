module org.jjspizzeria.jjspizzeria {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.jjspizzeria.jjspizzeria to javafx.fxml;
    exports org.jjspizzeria.jjspizzeria;
}