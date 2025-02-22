package org.jjspizzeria.jjspizzeria.command;

import java.util.ArrayDeque;
import java.util.Deque;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PizzaCommandInvoker {
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private HBox hbox;


    public PizzaCommandInvoker() {
        hbox = new HBox(20);
        GridPane toppingBar = createToppingBar();
        VBox operationsBar = createOperationsBar();

        hbox.getChildren().add(toppingBar);
        hbox.getChildren().add(operationsBar);
        hbox.getStyleClass().add("pizza-control-panel");
    }

    private Button createToppingButton(String buttonImageFilename) {
        Button button = new Button("", createButtonIcon(buttonImageFilename));
        button.getStyleClass().add("topping-button");
        return button;
    }

    private GridPane createToppingBar() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(7);
        gridPane.setVgap(7);
        gridPane.setPadding(new Insets(15));

        Button cheeseButton = createToppingButton("cheese.png");
        cheeseButton.setOnAction(e -> executeCommand(new AddCheeseCommand()));

        Button jalapenoButton = createToppingButton("jalapenos.png");
        jalapenoButton.setOnAction(e -> executeCommand(new AddJalapenoCommand()));

        Button hamButton = createToppingButton("ham.png");
        hamButton.setOnAction(e -> executeCommand(new AddHamCommand()));

        Button mushroomButton = createToppingButton("mushrooms.png");
        mushroomButton.setOnAction(e -> executeCommand(new AddMushroomCommand()));

        Button pineappleButton = createToppingButton("pineapple.png");
        // pineappleButton.setOnAction(e -> executeCommand(new AddPineappleCommand()));

        Button pepperoniButton = createToppingButton("pepperoni.png");
        pepperoniButton.setOnAction(e -> executeCommand(new AddPepperoniCommand()));

        Button tomatoesButton = createToppingButton("tomato.png");
        // tomatoesButton.setOnAction(e -> executeCommand(new AddTomatoCommand()));

        Button onionButton = createToppingButton("onions.png");
        onionButton.setOnAction(e -> executeCommand(new AddOnionCommand()));

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

        Button normalBake = new Button("Normal");
        normalBake.setOnAction(e -> System.out.println("Normal bake pressed"));
        normalBake.getStyleClass().add("bake-button");

        Button crispyBake = new Button("Crispy");
        crispyBake.setOnAction(e -> System.out.println("Crispy bake pressed"));
        crispyBake.getStyleClass().add("bake-button");

        HBox bakeOptions = new HBox(8, normalBake, crispyBake);
        bakeOptions.setAlignment(Pos.CENTER); // Center buttons horizontally

        Label bake = new Label("Bake");
        bake.getStyleClass().add("heading-label");

        VBox bakeBar = new VBox(bake, bakeOptions);
        bakeBar.getStyleClass().add("operation-box");



        //Slice HBox
        Button slice4 = new Button("4");
        slice4.setOnAction(e -> System.out.println("Slicing to 4"));
        slice4.getStyleClass().add("slice-button");

        Button slice6 = new Button("6");
        slice6.setOnAction(e -> System.out.println("Slicing to 6"));
        slice6.getStyleClass().add("slice-button");

        Button slice8 = new Button("8");
        slice8.setOnAction(e -> System.out.println("Slicing to 8"));
        slice8.getStyleClass().add("slice-button");

        HBox sliceOptions = new HBox(8, slice4, slice6, slice8);
        sliceOptions.setAlignment(Pos.CENTER);

        Label slice = new Label("Slice");
        slice.getStyleClass().add("heading-label");

        VBox sliceBar = new VBox (10, slice, sliceOptions);
        sliceBar.getStyleClass().add("operation-box");



        Button boxButton = new Button("Box");
        boxButton.setOnAction(e -> System.out.println("Boxing pizza"));
        boxButton.getStyleClass().add("end-button");

        Button finishButton = new Button("Finish");
        finishButton.setOnAction(e -> System.out.println("Finishing pizza"));
        finishButton.getStyleClass().add("end-button");

        HBox actionButtons = new HBox(10, boxButton, finishButton);
        actionButtons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, bakeBar, sliceBar, actionButtons);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center; -fx-spacing: 10;");
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

