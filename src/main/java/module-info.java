module com.example.jjspizzeria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jjspizzeria to javafx.fxml;
    exports com.example.jjspizzeria;
}