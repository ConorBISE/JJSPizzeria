package org.jjspizzeria.jjspizzeria.components;

import java.util.Stack;

import org.jjspizzeria.jjspizzeria.command.AddCheeseCommand;
import org.jjspizzeria.jjspizzeria.command.AddHamCommand;
import org.jjspizzeria.jjspizzeria.command.AddJalapenoCommand;
import org.jjspizzeria.jjspizzeria.command.AddMushroomCommand;
import org.jjspizzeria.jjspizzeria.command.AddOnionCommand;
import org.jjspizzeria.jjspizzeria.command.AddPepperoniCommand;
import org.jjspizzeria.jjspizzeria.command.Command;
import org.jjspizzeria.jjspizzeria.framework.FXMLComponent;

import javafx.scene.layout.HBox;

public class PizzaCommandBar extends FXMLComponent {

    private final Stack<Command> undoStack = new Stack<>();
    private HBox hbox;

    public PizzaCommandBar() {
        super("/org/jjspizzeria/jjspizzeria/layouts/pizzaCommandBar.fxml");
    
        // TODO: re-add command handling logic
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
