package com.example.jjspizzeria;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    TextArea consoleArea;
    GameConsole console;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JJ's Pizzeria");

        console = GameConsole.getInstance();
        consoleArea = console.getTextArea();

        // Banner
        Image bannerImage = new Image(getClass().getResourceAsStream("images/banner.png"));
        ImageView bannerView = new ImageView(bannerImage);
        bannerView.setFitWidth(400);
        bannerView.setPreserveRatio(true);

        // StackPane for layering
        StackPane stackPane = new StackPane();

        // Add console first, then banner so banner is "on top" in overlapping area
        stackPane.getChildren().addAll(consoleArea, bannerView);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setMaxHeight(500);
        stackPane.setMaxWidth(660);

        StackPane.setAlignment(consoleArea, Pos.TOP_CENTER);
        StackPane.setAlignment(bannerView, Pos.TOP_CENTER);

        // Shift the console down so its top is behind the banner
        StackPane.setMargin(consoleArea, new Insets(60, 0, 0, 0));

        Button button = new Button("Click Me");
        button.setOnAction(e -> console.append("Button clicked!"));

        // Lay everything out
        BorderPane root = new BorderPane();
        root.setCenter(stackPane);
        root.setBottom(button);

        // Scene & stage
        Scene scene = new Scene(root, 760, 760);
        scene.getStylesheets()
                .add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        console.append("Welcome to JJ's Pizzeria!");
    }


    public static void main(String[] args) {
        launch();
    }
}

