package org.jjspizzeria.jjspizzeria.components;

import java.util.Map;
import java.util.Stack;

import org.jjspizzeria.jjspizzeria.command.*;
import org.jjspizzeria.jjspizzeria.framework.FXMLComponent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PizzaCommandBar extends FXMLComponent {

    private final Stack<Command> undoStack = new Stack<>();

    private Map<String, Command> toppingCommandMapping;

    public PizzaCommandBar() {
        super("/org/jjspizzeria/jjspizzeria/layouts/pizzaCommandBar.fxml");
    
        toppingCommandMapping = Map.of(
            "cheese", new AddCheeseCommand(),
            "jalapenos", new AddJalapenoCommand(),
            "ham", new AddHamCommand(),
            "mushrooms", new AddMushroomCommand(),
            "pineapple", new AddPineappleCommand(),
            "pepperoni", new AddPepperoniCommand(),
            "tomato", new AddTomatoCommand(),
            "onions", new AddOnionCommand()
        );
    }

    @FXML
    private void onAddTopping(ActionEvent e) {
        IconButton button = (IconButton) e.getSource();
        executeCommand(toppingCommandMapping.get(button.getIconName()));
    }


    @FXML
    private void bakeNormal() {
        executeCommand(new BakeCommand("normal"));
    }

    @FXML
    private void bakeCrispy() {
        executeCommand(new BakeCommand("crispy"));
    }

    @FXML
    private void slice4() {
        executeCommand(new SliceCommand(4));
    }

    @FXML
    private void slice6() {
        executeCommand(new SliceCommand(6));
    }

    @FXML
    private void slice8() {
        executeCommand(new SliceCommand(8));
    }

    @FXML
    private void box() {
        executeCommand(new BoxCommand());
    }

    @FXML
    private void finish() {
        System.out.println("Finishing pizza");
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
}
