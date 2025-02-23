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
        Button inner = (Button)e.getSource();
        IconButton outer = (IconButton)inner.getParent();
        executeCommand(toppingCommandMapping.get(outer.getIcon()));
    }
    
    @FXML
    private void bakeNormal() {
        // TODO
        System.out.println("Bake normal");
    }

    @FXML
    private void bakeCrispy() {
        // TODO
        System.out.println("Bake crispy");
    }

    @FXML
    private void slice4() {
        // TODO
        System.out.println("Slicing to 4");
    }

    @FXML
    private void slice6() {
        // TODO
        System.out.println("Slicing to 6");
    }

    @FXML
    private void slice8() {
        // TODO
        System.out.println("Slicing to 8");
    }

    @FXML
    private void box() {
        // TODO
        System.out.println("Boxing pizza");
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
