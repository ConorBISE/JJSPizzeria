package org.jjspizzeria.jjspizzeria.command;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Stack;

public class PizzaCommandInvoker {
    private final Stack<Command> undoStack = new Stack<>();
    private HBox hbox;

    public PizzaCommandInvoker() {
        hbox = new HBox(20);
        GridPane toppingBar = createToppingBar();
        VBox operationsBar = createOperationsBar();

        hbox.getChildren().add(toppingBar);
        hbox.getChildren().add(operationsBar);
        hbox.getStyleClass().add("pizza-control-panel");
    }

    private GridPane createToppingBar() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(7);
        gridPane.setVgap(7);
        gridPane.setPadding(new Insets(15));

        Button cheeseButton = new Button("", createButtonIcon("cheese.png"));
        cheeseButton.setOnAction(e -> executeCommand(new AddCheeseCommand()));
        cheeseButton.getStyleClass().add("topping-button");

        Button jalapenoButton = new Button("", createButtonIcon("jalapenos.png"));
        jalapenoButton.setOnAction(e -> executeCommand(new AddJalapenoCommand()));
        jalapenoButton.getStyleClass().add("topping-button");

        Button hamButton = new Button("", createButtonIcon("ham.png"));
        hamButton.setOnAction(e -> executeCommand(new AddHamCommand()));
        hamButton.getStyleClass().add("topping-button");

        Button mushroomButton = new Button("", createButtonIcon("mushrooms.png"));
        mushroomButton.setOnAction(e -> executeCommand(new AddMushroomCommand()));
        mushroomButton.getStyleClass().add("topping-button");

        Button pineappleButton = new Button("", createButtonIcon("pineapple.png"));
        // pineappleButton.setOnAction(e -> executeCommand(new AddPineappleCommand()));
        pineappleButton.getStyleClass().add("topping-button");

        Button pepperoniButton = new Button("", createButtonIcon("pepperoni.png"));
        pepperoniButton.setOnAction(e -> executeCommand(new AddPepperoniCommand()));
        pepperoniButton.getStyleClass().add("topping-button");

        Button tomatoesButton = new Button("", createButtonIcon("tomato.png"));
        // tomatoesButton.setOnAction(e -> executeCommand(new AddTomatoCommand()));
        tomatoesButton.getStyleClass().add("topping-button");

        Button onionButton = new Button("", createButtonIcon("onions.png"));
        onionButton.setOnAction(e -> executeCommand(new AddOnionCommand()));
        onionButton.getStyleClass().add("topping-button");

        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> undoLastCommand());
        undoButton.getStyleClass().add("topping-button");

        // Row 0
        gridPane.add(cheeseButton,     0, 0);
        gridPane.add(jalapenoButton,     1, 0);
        gridPane.add(hamButton,        2, 0);
        // Row 1
        gridPane.add(mushroomButton,   0, 1);
        gridPane.add(pineappleButton,  1, 1);
        gridPane.add(pepperoniButton,  2, 1);
        // Row 2
        gridPane.add(tomatoesButton,   0, 2);
        gridPane.add(onionButton,      1, 2);
        gridPane.add(undoButton,     2, 2);

        return gridPane;
    }

    private VBox createOperationsBar() {
        // TODO: create panel for baking, slicing, and boxing the pizza
        return new VBox(20);
    }

    public HBox getHBox() {
        return hbox;
    }

    public void executeCommand(Command command) {
        System.out.println("Executing command: " + command); // TODO: remove this debug print
        command.execute();
        if (command.isUndoable()) {
            undoStack.push(command);
        }
    }

    public void undoLastCommand() {
        System.out.println("Executing command undoing last command"); // TODO: remove this debug print
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
        }
    }

    // Helper function
    public ImageView createButtonIcon(String imageFilename) {
        String resourcePath = "/org/jjspizzeria/jjspizzeria/images/" + imageFilename;

        Image image = new Image(getClass().getResourceAsStream(resourcePath));
        ImageView icon = new ImageView(image);
        icon.setPreserveRatio(true);
        icon.setFitWidth(65);
        return icon;
    }
}

