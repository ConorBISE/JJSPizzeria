package org.jjspizzeria.jjspizzeria;

import java.io.IOException;

import org.jjspizzeria.jjspizzeria.framework.View;
import org.jjspizzeria.jjspizzeria.views.GameView;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JJ's Pizzeria");

        View view = new GameView();
        view.show(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
