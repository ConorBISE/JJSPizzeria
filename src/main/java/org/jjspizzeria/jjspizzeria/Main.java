package org.jjspizzeria.jjspizzeria;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jjspizzeria.jjspizzeria.themes.ThemeFactory;
import org.jjspizzeria.jjspizzeria.themes.ThemeManager;


public class Main extends Application {
    TextArea consoleArea;
    GameConsole console;
    ThemeManager themeManager;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JJ's Pizzeria");

        console = GameConsole.getInstance();
        consoleArea = console.getTextArea();

        themeManager = ThemeManager.getInstance();
        ThemeFactory currentTheme = themeManager.getCurrentTheme();

        // Banner
        ImageView bannerView = currentTheme.createBanner();
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


        // Lay everything out
        BorderPane root = new BorderPane();
        root.setCenter(stackPane);
        root.getStyleClass().add(currentTheme.getBackgroundCSSClass());

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

