package org.jjspizzeria.jjspizzeria;

import org.jjspizzeria.jjspizzeria.screens.WelcomeScreen;
import org.jjspizzeria.jjspizzeria.framework.Screen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JJ's Pizzeria");

        Scene scene = new Scene(new WelcomeScreen());


        primaryStage.setResizable(false);

        scene.getStylesheets()

            .add(
                getClass()
                .getResource("/org/jjspizzeria/jjspizzeria/styles.css")
                .toExternalForm()
            );

        primaryStage.addEventHandler(Screen.RESIZE_WINDOW, e -> primaryStage.sizeToScene());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
