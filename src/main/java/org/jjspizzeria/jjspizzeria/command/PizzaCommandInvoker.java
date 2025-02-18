package org.jjspizzeria.jjspizzeria.command;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

        //Bake HBox
        Button normalBake = new Button("Normal");
        normalBake.setOnAction(e -> System.out.println("Normal bake pressed"));

        Button crispyBake = new Button("Crispy");
        crispyBake.setOnAction(e -> System.out.println("Crispy bake pressed"));

        HBox bakeOptions = new HBox(normalBake, crispyBake);
        VBox bakeBar = new VBox (10, new Label("Bake"), bakeOptions);
        bakeBar.setAlignment(Pos.CENTER);


        //Slice HBox
        Button slice4 = new Button("4");
        slice4.setOnAction(e -> System.out.println("Slicing to 4"));

        Button slice6 = new Button("6");
        slice6.setOnAction(e -> System.out.println("Slicing to 6"));

        Button slice8 = new Button("8");
        slice8.setOnAction(e -> System.out.println("Slicing to 8"));

        HBox sliceOptions = new HBox(10, slice4, slice6, slice8);
        VBox sliceBar = new VBox (10, new Label("Slice"), sliceOptions);
        sliceBar.setAlignment(Pos.CENTER);



        Button boxButton = new Button("Box");
        boxButton.setOnAction(e -> System.out.println("Boxing pizza"));

        Button finishButton = new Button("Finish");
        finishButton.setOnAction(e -> System.out.println("Finishing pizza"));

        HBox actionButtons = new HBox(10, boxButton, finishButton);

        VBox layout = new VBox(20, bakeBar, sliceBar, actionButtons);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-spacing: 20;");
        return layout;
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

